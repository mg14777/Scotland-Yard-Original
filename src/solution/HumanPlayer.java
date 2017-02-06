package solution;

import java.util.List;
import scotlandyard.*;
import scotlandyard.Colour;
import scotlandyard.Move;
import scotlandyard.MoveDouble;

public class HumanPlayer implements Player{
	ScotlandYardApplication controller;
    /**
     * Notifies the Model class that a move has been chosen by the Player
     */
	public Move notify(int location, List<Move> list){
		controller.index = -1;
		controller.view.currentMoveList(list);
		controller.setCurrentMoves(list);
		controller.updateViewerList();
		controller.view.timer();
		controller.locationToDraw();
		while(controller.index == -1) {
			try{Thread.sleep(10);}catch(Exception e){ System.out.println("Error");}
		}
		
		list = controller.getCurrentMoves();
		if(controller.model.getCurrentPlayer().equals(Colour.Black)) {
			controller.view.xlog.update(list.get(controller.index),controller.model.getRound());
		}
		if(controller.model.getCurrentPlayer().equals(Colour.Black)) {
			if(list.get(controller.index)instanceof MoveDouble)
				controller.view.side.updateRound(1,controller.model.getRound());
			else
				controller.view.side.updateRound(0,controller.model.getRound());
		}
		controller.view.game.resetDrawClick();
		return list.get(controller.index);
	}
	public void register(ScotlandYardApplication controller) {
		this.controller = controller;
		
	}
}