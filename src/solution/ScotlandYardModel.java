package solution;

import scotlandyard.*;
import java.util.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ScotlandYardModel extends ScotlandYard {
    private List<RealPlayer> players = new ArrayList<RealPlayer>();
    private List<Boolean> rounds;
    private Boolean winnerMrX;                                         //true if mrX wins, false if detectives win
    private int mrxLastLocation = 0;
    private RealPlayer currentPlayer;
    private List<Spectator> spectators = new ArrayList<Spectator>();
    private int roundCounter = 0;
    private int numberOfDetectives;
    private Graph map;
    private Move currentMove = null;
    private int x = 0;          //current player index
    private List<Move> movesDoneSinceReveal = new ArrayList<Move>();
    
    public ScotlandYardModel(int numberOfDetectives, List<Boolean> rounds,      
     String graphFileName) throws IOException {                                 
        super(numberOfDetectives, rounds, graphFileName);
        ScotlandYardGraphReader read = new ScotlandYardGraphReader();
        this.numberOfDetectives = numberOfDetectives;
        this.rounds = rounds;
        try {map = read.readGraph(graphFileName);}
        catch(Exception e) { System.out.println("Inexistent file");}
    }
    
    @Override
    protected Move getPlayerMove(Colour colour) {
        Move move;
        for(RealPlayer player : players) {
            if(player.colour.equals(colour))
                currentPlayer = player;
        }
        move = currentPlayer.player.notify(currentPlayer.location, validMoves(colour));
        currentMove = move;
        return move;
    }
    
    @Override
    protected void nextPlayer() {
        x++;
    }
    @Override
    protected void play(MoveTicket move) {          
        for(RealPlayer player: players)
            if(player.colour == move.colour) {
                player.tickets.put(move.ticket, getPlayerTickets(move.colour, move.ticket) - 1);
                player.location = move.target; 
            }
        if(move.colour!= Colour.Black)                                          //if detective -> add used ticket to MrX
            players.get(0).tickets.put(move.ticket, getPlayerTickets(Colour.Black, move.ticket) + 1);
        else
            roundCounter++;                                                     //if MrX -> go to next round
        for(Spectator spectator: spectators)
            if(rounds.get(roundCounter))
                spectator.notify(move);
            else
                spectator.notify(new MoveTicket(move.colour, mrxLastLocation, move.ticket));
        if(move.colour == Colour.Black && rounds.get(getRound())) {
            movesDoneSinceReveal.clear();
            mrxLastLocation = move.target;
        }
        else 
            if(move.colour == Colour.Black)
                movesDoneSinceReveal.add(move);
    }
    
    @Override
    protected void play(MoveDouble move) {
        players.get(0).tickets.put(Ticket.DoubleMove, getPlayerTickets(Colour.Black, Ticket.DoubleMove) - 1);
        for(Spectator spectator: spectators)
            spectator.notify(move);
        MoveTicket move1 = (MoveTicket) move.moves.get(0);
        MoveTicket move2 = (MoveTicket) move.moves.get(1);
        play(move1);
        play(move2);
        
    }
    
    @Override
    protected void play(MovePass move) {
        for(Spectator spectator: spectators)
            spectator.notify(move);
    }
    /**
    * @return Checks to see if detective is on MrX
    * 
    */
    protected boolean check(Colour colour,int target) {
        for(RealPlayer player : players) {
            if(!player.colour.equals(colour))
                if((player.location ==  target) && (!player.colour.equals(Colour.Black))) {
                    return false;
                }
        }
        return true;
    }
    /**
     * 
     * @param colour Colour of Player
     * @param target1 Location for Move 1
     * @param target2 Location for Move 2
     * @param route1  Transport for Move 1
     * @param route2  Transport for Move 2
     * @param secretmoves No. of Secret Move Tickets
     * @return Creates double secret moves for MrX
     */
    protected List<Move> doublesecretMoves (Colour colour, int target1, int target2,
     Edge<Integer,Route> route1, Edge<Integer,Route> route2, int secretmoves) {
        MoveTicket move1 = new MoveTicket(colour,target1,Ticket.fromRoute(route1.data()));
        MoveTicket move2 = new MoveTicket(colour,target2,Ticket.fromRoute(route2.data()));
        MoveTicket move1secret = new MoveTicket(colour,target1,Ticket.SecretMove);
        MoveTicket move2secret = new MoveTicket(colour,target2,Ticket.SecretMove);
        List<Move> doublesecretmoves = new ArrayList<Move>();
        if(secretmoves!=0) {
            doublesecretmoves.add(new MoveDouble(colour,move1secret,move2));
            doublesecretmoves.add(new MoveDouble(colour,move1,move2secret));
        }
        if(secretmoves>1)
            doublesecretmoves.add(new MoveDouble(colour,move1secret,move2secret));
        return doublesecretmoves;
    }
    /**
     * 
     * @param colour Colour of Player
     * @param target Target location of Move
     * @param route1 Transport of Move
     * @param secretmoves No. of Secret Move Tickets
     * @return All the possible double moves for MrX
     */
    protected List<Move> doublevalidMoves(Colour colour, int target,
     Edge<Integer,Route> route1, int secretmoves) {
        List<Edge<Integer, Route>> routes2 = new ArrayList<Edge<Integer,Route>>(map.getEdges(target));
        List<Move> doublemoves = new ArrayList<Move>();
        for(Edge<Integer,Route> route2 : routes2 ) {
            MoveTicket move1 = new MoveTicket(colour, target,Ticket.fromRoute(route1.data()));
            if(route1.data().equals(route2.data())) {
                if(getPlayerTickets(colour,Ticket.fromRoute(route2.data())) > 1) {
                    if((route2.target()).equals(target)) {
                        MoveTicket move2 = new MoveTicket(colour, route2.source(), Ticket.fromRoute(route2.data()));
                        if(check(colour,route2.source())) {
                            doublemoves.add(new MoveDouble(colour, move1, move2));
                            doublemoves.addAll(doublesecretMoves(colour, target, route2.source(), route1, route2, secretmoves));
                        }
                    }
                    else {
                        MoveTicket move2 = new MoveTicket(colour,route2.target(),Ticket.fromRoute(route2.data()));
                        if(check(colour,route2.target())) {
                            doublemoves.add(new MoveDouble(colour,move1,move2));
                            doublemoves.addAll(doublesecretMoves(colour, target, route2.target(), route1, route2, secretmoves));
                        }
                    }
                }
            }
            else {
                if(getPlayerTickets(colour, Ticket.fromRoute(route2.data())) != 0) {
                    if((route2.target()).equals(target)) {
                        MoveTicket move2 = new MoveTicket(colour, route2.source(),Ticket.fromRoute(route2.data()));
                        if(check(colour, route2.source())) {
                            doublemoves.add(new MoveDouble(colour,move1,move2));
                            doublemoves.addAll(doublesecretMoves(colour,target,route2.source(), route1, route2, secretmoves));
                        }
                    }
                    else {
                        MoveTicket move2 = new MoveTicket(colour,route2.target(),Ticket.fromRoute(route2.data()));
                        if(check(colour, route2.target())) {
                            doublemoves.add(new MoveDouble(colour, move1, move2));
                            doublemoves.addAll(doublesecretMoves(colour, target, route2.target(), route1, route2, secretmoves));
                        }
                    }
                }
            }
        }
        return doublemoves;
    }
    
    @Override
    protected List<Move> validMoves(Colour player) {
        int location = getPlayer(player).location;
        int flag = 0;
        if(player.equals(Colour.Black))
            flag = 1;
        List<Edge<Integer, Route>> routes = new ArrayList<Edge<Integer,Route>>(map.getEdges(location));
        List<Move> moves = new ArrayList<Move>();
        int doublemovetickets = getPlayerTickets(player,Ticket.DoubleMove);
        int secretmovetickets = getPlayerTickets(player,Ticket.SecretMove);
        for(Edge<Integer,Route> route : routes ) {
            if(getPlayerTickets(player,Ticket.fromRoute(route.data())) != 0) {
                if((route.target()).equals(location)) {
                    if(check(player,route.source())) {
                        moves.add(new MoveTicket(player, route.source(),Ticket.fromRoute(route.data())));
                        if(flag==1 && secretmovetickets!=0)
                            moves.add(new MoveTicket(player, route.source(),Ticket.SecretMove));
                    }
                    if(flag==1 && check(player,route.source()) && doublemovetickets!=0)
                        moves.addAll(doublevalidMoves(player, route.source(), route, secretmovetickets));
                }
                else {
                    if(check(player,route.target())) {
                        moves.add(new MoveTicket(player,route.target(),Ticket.fromRoute(route.data())));
                        if(flag==1 && secretmovetickets!=0)
                            moves.add(new MoveTicket(player,route.target(),Ticket.SecretMove));
                    }
                    if(flag==1 && check(player,route.target()) && doublemovetickets!=0)
                        moves.addAll(doublevalidMoves(player, route.target(), route, secretmovetickets));
                }
            }
        }
        if(moves.size() == 0&&!player.equals(Colour.Black))
            moves.add(new MovePass(player));
        return moves;
    }
    
    @Override
    public void spectate(Spectator spectator) {
        spectators.add(spectator);
    }
    
    @Override
    public boolean join(Player player, Colour colour, int location, Map<Ticket, Integer> tickets) {
        if(players.size()<=numberOfDetectives+1) {
            if(colour.equals(Colour.Black))
                players.add(0, new RealPlayer(player, colour, location, tickets));
            else
                players.add(new RealPlayer(player, colour, location, tickets));
            return true;
        }
        else
            return false;
    }
    
    @Override
    public List<Colour> getPlayers() {
        List<Colour> colours = new ArrayList<Colour>();
        for(RealPlayer player: players)
            colours.add(player.colour);
        return colours;
    }
    /**
     * 
     * @return True if all detectives can't move
     */
    private boolean frozenDetectives() {
        int numberOfFrozenDetectives = 0;
        for(RealPlayer player: players)
            if(player.colour != Colour.Black && validMoves(player.colour).get(0) instanceof MovePass)
            	numberOfFrozenDetectives++;
        if(numberOfFrozenDetectives == numberOfDetectives)
            return true;
        else
            return false;
    }
    /**
     * 
     * @return True if Mr.X can't move
     */
    private boolean frozenMrX() {
            if(validMoves(Colour.Black).size()==0)
                return true;
            else
                return false;
    }

    @Override
    public Set<Colour> getWinningPlayers() {
        Set<Colour> winners = new HashSet<Colour>();
        if(isGameOver())
            if(winnerMrX)
                winners.add(Colour.Black);
            else 
                for(RealPlayer player : players)
                    if(!player.colour.equals(Colour.Black))
                        winners.add(player.colour);
        return winners;
    }
    
    @Override
    public int getPlayerLocation(Colour colour) {
        if(colour.equals(Colour.Black)) {
            if(rounds.get(roundCounter))
                mrxLastLocation = players.get(0).location;
        }
        else
            for(RealPlayer player: players)
                if(player.colour.equals(colour))
                    return player.location;
        return mrxLastLocation;
    }
    
    @Override
    public int getPlayerTickets(Colour colour, Ticket ticket) {
        for(RealPlayer player: players)
            if(player.colour.equals(colour))
                return player.tickets.get(ticket);
        return 0;
    }
    
    @Override
    public boolean isGameOver() {
        if(players.size() == 0)
            return false;
        if(frozenMrX()) {
            winnerMrX = false;
            return true;
        }
        for(RealPlayer player : players)
            if((players.get(0).location == player.location) && !player.colour.equals(Colour.Black)) {
                winnerMrX = false;
                return true;
            }    
        if((rounds.size()==roundCounter)|| frozenDetectives()||(rounds.size()==(roundCounter+1)&&x==numberOfDetectives+1)) {
            winnerMrX = true;
            return true;
        }
        return false;
    }
    
    @Override
    public boolean isReady() {
        if(players.size() - 1 == numberOfDetectives)
            return true;
        return false;
    }
    
    @Override
    public Colour getCurrentPlayer() {
        if(x>numberOfDetectives)
            x = x%(numberOfDetectives+1);
        if(x==0)
            return Colour.Black;
        else
            return players.get(x).colour;
    }
    
    @Override
    public int getRound() {
        return roundCounter;
    }
    
    @Override
    public List<Boolean> getRounds() {
        return rounds;
    }
    /**
     * 
     * @param colour Colour of the Player to be returned
     * @return Specified player on basis of Colour
     */
    public RealPlayer getPlayer(Colour colour) {
        for(RealPlayer player: players)
            if(player.colour.equals(colour))
                return player;
        return null;
    }
    /**
     * sets the current player when you load game
     * @param playerToBeSet Sets given player as current player
     */
    public void setCurrentPlayer(Colour playerToBeSet) {           
        for(RealPlayer player: players)
            if(player.colour.equals(playerToBeSet))
                x = players.indexOf(player);
    }
  /**
   * Removes a player from the game
   * @param playerToDelete Player to be deleted from the game
   */
    public void removePlayer(Colour playerToDelete) {
    	Iterator<RealPlayer> iter = players.iterator();
    	while(iter.hasNext()) {
    		RealPlayer str = iter.next();
    		if(str.colour.equals(playerToDelete))
                iter.remove();
    	}
                
    }
    /**
     * Removes all Players from the game
     */
    public void removeAllPlayers() {
    	Iterator<RealPlayer> iter = players.iterator();
    	while(iter.hasNext()) {
    		RealPlayer str = iter.next();
            iter.remove();
    	}
    }
    
    /**
     * 
     * @param moves All Valid Moves for a player
     * @return All the locations where a player can go 
     */
    public Set<Integer> possibleDestinations(List<Move> moves) {
        Set<Integer> destinations = new HashSet<Integer>();
        for(Move move: moves)
            if(move instanceof MoveTicket) 
                destinations.add(((MoveTicket) move).target);
            else if(move instanceof MoveDouble) 
                destinations.add(((MoveTicket)(((MoveDouble) move).moves.get(1))).target);
        return destinations;
    }
 
    Move getCurrentMove() {
        return currentMove;
    }
    /**
     * 
     * @return MrX's actual location
     */
    public int mrxActual() {
    	return players.get(0).location;
    }
    
  /**
   * 
   * @return All the possible location of mrX based on when he last revealed and used tickets
   */
    public Set<Integer> MrXpossibleLocations() {
        if(movesDoneSinceReveal!=null) {
            Set<Integer> mrxPossibleLocations = new HashSet<Integer>();
            mrxPossibleLocations.add(mrxLastLocation);
            for(Move move:movesDoneSinceReveal)
                if(move instanceof MoveDouble) {
                    movesDoneSinceReveal.add(((MoveDouble) move).moves.get(0));
                    movesDoneSinceReveal.add(((MoveDouble) move).moves.get(1));
                    movesDoneSinceReveal.remove(move);
                }
            for(Move move: movesDoneSinceReveal) {
                Set<Integer> aux = new HashSet<Integer>();
                for(Integer p: mrxPossibleLocations) {
                    List<Edge<Integer, Route>> routes = new ArrayList<Edge<Integer,Route>>(map.getEdges(p));
                    for(Edge<Integer,Route> route : routes){
                        if(route.target()==p) {
                        	if(Ticket.fromRoute(route.data()).equals(((MoveTicket) move).ticket)||((MoveTicket) move).ticket==Ticket.SecretMove) 
                        		aux.add(route.source());
                        }
                        else
                            if(route.source()==p)
                            	if(Ticket.fromRoute(route.data()).equals(((MoveTicket) move).ticket)||((MoveTicket) move).ticket==Ticket.SecretMove)
                            		aux.add(route.target());
                    }
                }
                mrxPossibleLocations = aux;
            }
            return mrxPossibleLocations;
        }
        return null;
    }
}