
import appAncien.model.*;
import appAncien.vue.*;

public class MainTest {

    public static void main(String[] args){
        

        if(args.length >= 1){
            
            if(args[0].equals("terminal")){
                Orchestrator orch = new Orchestrator("human", "human", 20, 20, 1, false);
                orch.run();
            }
        }
        else{
            new FrameMenu();
        }
    }

    
}
