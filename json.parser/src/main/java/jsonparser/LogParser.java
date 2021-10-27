package jsonparser;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import DBUtility.DataBaseUtility;
import model.Logs;


public class LogParser {

	public static void main(String...args) throws IOException{
		FileInputStream logfile = new FileInputStream(args[0]);
		JsonFactory jf = new JsonFactory();
        JsonParser parser = jf.createParser(logfile);
        parser.setCodec(new ObjectMapper());
        parser.nextToken();
        Map<String,Logs> tracker = new HashMap<String,Logs>();
        while (parser.hasCurrentToken()) {
            Logs log = parser.readValueAs(Logs.class);
            if(tracker.containsKey(log.getId())){
            	Logs trackedLog = tracker.get(log.getId());
            	long jobDurationMs = Math.abs(trackedLog.getTimestamp()-log.getTimestamp());
            	boolean alert=false;
            	if(jobDurationMs>4){
            		alert=true;
            	}
            	DataBaseUtility dbUtility=new DataBaseUtility();
            	dbUtility.addRow(trackedLog.getId(), String.valueOf(jobDurationMs), trackedLog.getType(), trackedLog.getHost(), String.valueOf(alert));
        		tracker.remove(log.getId());
            }
            else{
            	tracker.put(log.getId(), log);
            }
            parser.nextToken();
        }
	}
	
}
