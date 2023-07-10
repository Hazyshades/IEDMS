import Folders.CopyFolders;
import Logs.LogReader;
import Services.ServicesStarting;

public class RunTest {
    public static void main(String[] args) {
        CopyFolders.incomingCopyContainer();
        ServicesStarting.startService("IEDMSInterfaceService");
        LogReader.validateLog();
        Helpers.Waiting(10);
        ServicesStarting.stopService("IEDMSInterfaceService");

    }
}
