package view.Commands;

/**
 * @author lilred
 * @date 2023/07/18
 **/
public class RunCommand {
    //this class is used to take in subclasses of DraWAll class
    // to call their run() and addToHistory() methods
    private DrawAll dra;

    public  RunCommand(DrawAll dra){
        this.dra = dra;
    }

    public void execute(){
        this.dra.run();
        this.dra.addToHistory();
    }

}
