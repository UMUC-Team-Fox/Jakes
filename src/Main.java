import java.io.IOException;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.UploadErrorException;


public class Main {

	
	public static void main(String[] args) throws IOException, UploadErrorException, DbxException {
		// TODO Auto-generated method stub
		Memeagram m = new Memeagram();
		DropBoxController dbc = new DropBoxController();
		try {dbc.connect();} catch(DbxException e) {}
		dbc.uploadTestFile();
	}

}
