package v2.component.helper.factory;

import v2.component.gameObject.immovable.bonus.Bonus;
import v2.view.GameView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BonusFactory {

    private List<Bonus> listBonus = new ArrayList<>();;
    private int exist ;
    private Bonus bonus;

    public BonusFactory(){
        //removeBonus();
    }

    public void tryRemoveBonus(){

        if(listBonus!=null)
            for (int i=0;i<listBonus.size();i++){
                if(listBonus.get(i).getTimeLeft() <=0)
                    listBonus.remove(i);
            }
    }

    public void update()  {

        bonus = new Bonus(4400);

        if(listBonus.size()==0) {
            listBonus.add(bonus);
            GameView.listTimeLeft.add(bonus.getTimeLeft());
        }
        else {
            if (!checkList(bonus)) {
                listBonus.add(bonus);
                GameView.listTimeLeft.add( bonus.getTimeLeft());
            } else {
                listBonus.get(exist).setTimeLeft(4400);
            }
        }
        tryRemoveBonus();
    }

    public List<Bonus> getlistBonus(){
        return listBonus;
    }

    public Bonus getBonus(int i){
        return listBonus.get(i);
    }
    private boolean checkList(Bonus bonus){
        if(listBonus.size()==0) return true;
        else {
        for(int i=0;i<listBonus.size();i++) {
            if (listBonus.get(i).getColor() == bonus.getColor()) {
                exist=i;
                return true;
            }
        }
                return false;
    }
    }

}
