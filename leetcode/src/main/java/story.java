/**
 * @author JYP
 * @date 2021/8/2
 **/

public class story {

    public void tellStory(int i){
        if(i==50)
            return;
        System.out.print("从前有座山 山里有座庙 庙里有个老和尚和小和尚讲故事讲的是");
        tellStory(i++);
    }

    public static void main(String[] args) {
        new story().tellStory(0);
    }
}
