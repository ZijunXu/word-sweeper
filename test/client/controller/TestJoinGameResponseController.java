package client.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import client.MockServerAccess;
import client.controller.JoinGameResponseController;
import client.model.Model;
import client.view.Application;
import xml.Message;
import junit.framework.TestCase;

/**
 * this test case is responsible for testing join game response controller
 * @author Zhanfeng Huang
 * 
 */
public class TestJoinGameResponseController extends TestCase {
	
	// Mock server object that extends (and overrides) ServerAccess for its purposes
	MockServerAccess mockServer;
	
	// client to connect
	Application client;
	
	// model being maintained by client.
	Model model;

	
	protected void setUp() {
		// FIRST thing to do is register the protocol being used.
		if (!Message.configure("wordsweeper.xsd")) {
			fail ("unable to configure protocol");
		}
		
		// prepare client and connect to server.
		model = new Model();
		
		
		client = new Application (model);
		client.setVisible(true);
		
		// Create mockServer to simulate server, and install 'obvious' handler
		// that simply dumps to the screen the responses.
		mockServer = new MockServerAccess("localhost");
		
		// as far as the client is concerned, it gets a real object with which
		// to communicate.
		client.setServerAccess(mockServer);
	}
	
	/**
	 * It is for the test case of JoinGameResponseController
	 * initialize a room number to generate the simulating xmlString response
	 * check if the game's ID is set correctly
	 */
	public void testJoinGameResponseProcess() {
		
		String roomNumber = "1";
		
		String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><response id=\"RandomUUID\" success=\"true\">" + String.format("<joinGameResponse gameId=\"%s\">" + "</joinGameResponse></response>", roomNumber);
		
		System.out.println(xmlString);
		
		Message m = new Message(xmlString);
		
		assertTrue (new JoinGameResponseController(client, model).process(m));
		assertEquals (roomNumber, model.getGame().getRoomID());
		
	}
}
