package com.own.utils;

public interface FilesPath extends Identification{

	@Key("application_path")
	String applicationPath();
	
	@Key("statusmsg")
	String StatusMessagePath();
	
	@Key("http_pool")
	String httpPoolPath();
	
	@Key("brn_path")
	String branPath();
	
	@Key("db_query")
	String dbQuery();
	
    @Key("npciresponse_codes")
	String npciresp();
}
