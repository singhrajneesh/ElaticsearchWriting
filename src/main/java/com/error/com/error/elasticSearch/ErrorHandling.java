package com.error.com.error.elasticSearch;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import java.io.IOException;
import java.util.Collections;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
/*Scala Import required for httpclient in scala
import java.io.IOException
import java.net.InetAddress
import java.text.{DateFormat, SimpleDateFormat}
import java.util.{Date, TimeZone}
import java.util.UUID.randomUUID
import java.util.Collections
import org.apache.http.HttpEntity
import org.apache.http.HttpHost
import org.apache.http.entity.ContentType
import org.apache.http.nio.entity.NStringEntity
import org.apache.http.util.EntityUtils
import org.elasticsearch.client.Response
import org.elasticsearch.client.RestClient
import org.slf4j.LoggerFactory
*/
public class ErrorHandling {
	public void writeOperations(String errorMessage) {
		
		/*TransportClient client = null;
        try {
			client = new PreBuiltTransportClient(Settings.EMPTY)
			        .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
			TimeZone tz = TimeZone.getTimeZone("UTC");
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'") ; // Quoted "Z" to indicate UTC, no timezone offset
			df.setTimeZone(tz);
			String nowAsISO = df.format(new Date());
			IndexResponse response = client.prepareIndex("error", "type")
			        .setSource(jsonBuilder()
			                .startObject()
			                .field("error", errorMessage)
			                .field("@timestamp",nowAsISO)
			                .endObject()
			        )
			        .get();
		} catch (IOException e) {
			e.printStackTrace();
		}
        client.close();*/
		
	/*
	SCALA CODE FOR CONNECTING ELATSICSEARCH

        val hostAddress = propMap.getOrElse("GCP_ES_HOST", "")
	val portNumber = propMap.getOrElse("GCP_ES_PORT", "").toInt
	var restClient: RestClient  = RestClient.builder(new HttpHost(hostAddress, portNumber, "http")).build();
	                
	val response: Response = restClient.performRequest("GET", "/",Collections.singletonMap("pretty", "true"));

	val tz: TimeZone = TimeZone.getTimeZone("UTC")
	val df: DateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'") // Quoted "Z" to indicate UTC, no timezone offset
	df.setTimeZone(tz)
	val nowAsISO: String = df.format(new Date())

	var entity:HttpEntity  = new NStringEntity(
	/* JSON TO BE INSERTED IN ELASTICSEARCH 
	"{\n" +
	"    \"@timestamp\" : \""+nowAsISO+"\",\n" +
	"    \"error_mode\" : \""+errorHandlingEntity.Mode+"\",\n" +
	"    \"analytic_tool\" : \""+errorHandlingEntity.Tool+"\",\n" +
	"    \"error_message\" : \""+errorHandlingEntity.Message+"\",\n" +
	"    \"error_trace\" : \""+errorHandlingEntity.Trace+"\",\n" +
	"    \"yarn_app_id\" : \""+errorHandlingEntity.YarnAppId+"\",\n" +
	"    \"process_code\" : \""+errorHandlingEntity.ProcessCode+"\",\n" +
	"    \"source\" : \""+errorHandlingEntity.Source+"\"\n" +      
        "}", ContentType.APPLICATION_JSON);

	val rand = randomUUID().toString 
	var indexResponse:Response  = restClient.performRequest("PUT","/logstash-cdp-gcp-log-vault/logs/"+rand,Collections.singletonMap("",""),entity)
	restClient.close();
	*/	
		RestClient restClient = RestClient.builder(new HttpHost("Internal_IP", 9200, "http")).build();
		Response response = restClient.performRequest("GET", "/",Collections.singletonMap("pretty", "true"));
		System.out.println(EntityUtils.toString(response.getEntity()));

		//index a document
		HttpEntity entity = new NStringEntity(
		        "{\n" +
		        "    \"user\" : \"kimchy\",\n" +
		        "    \"post_date\" : \"2009-11-15T14:12:12\",\n" +
		        "    \"message\" : \"trying out Elasticsearch\"\n" +
		        "}", ContentType.APPLICATION_JSON);

		Response indexResponse = restClient.performRequest("PUT","/logstash/tweet/3",Collections.<String, String>emptyMap(),entity);
		restClient.close();
	}
}
