package soccercoaching1.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import SoccerCoaching.Coach;
import SoccerCoaching.Player;
import SoccerCoaching.SoccerCoachingFactory;
import SoccerCoaching.SoccerCoachingPackage;
import SoccerCoaching.Team;
import SoccerCoaching.TrainingSession;

class SoccerCoaching {

	@Test
	void testAddingTeamToCoach() {
		SoccerCoachingPackage.eINSTANCE.eClass();
		
		Resource.Factory.Registry reg = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> m = reg.getExtensionToFactoryMap();
		m.put("xmi", new XMIResourceFactoryImpl());
		
		ResourceSetImpl resSet = new ResourceSetImpl();
		
		Resource coachResource = resSet.getResource(URI.createURI("instances/Coach.xmi"), true);
		Resource teamResource = resSet.getResource(URI.createURI("instances/Team.xmi"), true);
		Resource playerResource = resSet.getResource(URI.createURI("instances/Player.xmi"), true);
		Resource player2Resource = resSet.getResource(URI.createURI("instances/Player2.xmi"), true);
		Resource trainingSessionResource = resSet.getResource(URI.createURI("instances/TrainingSession.xmi"), true);

		TrainingSession trainingSession = (TrainingSession) trainingSessionResource.getContents().get(0);
		Coach coach = (Coach) coachResource.getContents().get(0);
		Team team = (Team) teamResource.getContents().get(0);
		Player player = (Player) playerResource.getContents().get(0);
		Player player2 = (Player) player2Resource.getContents().get(0);
		
		team.setTeamName("Werder Bremen");
		player.setName("Tim Flicke");
		player2.setName("Lukas Heitmann");
		coach.setName("Ole Werner");
		trainingSession.setTitle("Taktiktraining");
		trainingSession.setIntensity(0.5);
		trainingSession.setLength(1.5);
		player.setSkillLevel(55.5);
		player.participateInTraining(trainingSession);
		player2.setSkillLevel(50.0);
		player2.skipTrainingSession(trainingSession);
		coach.conductTrainingSession(trainingSession);
	
		
		player2.joinTeam(team);
		coach.assignTeam(team);
		player.joinTeam(team);

		Assertions.assertNull(player2.getCoach());
		Assertions.assertEquals(player.getCoach(), coach);
		
		System.out.println("New Team of Ole Werner: " + coach.getTeam().getTeamName());
		System.out.println("Werder Bremen has " + team.getCoaches().get(0).getName() + " as its coach!");
		System.out.println(coach.getName() + " conducts training " + coach.getTrainingsession().get(0).getTitle() 
				+ " with intensity " + coach.getTrainingsession().get(0).getIntensity() + " and length " 
				+ coach.getTrainingsession().get(0).getLength() + " and level " + coach.getTrainingLevel());

		
//		System.out.println("Werder Bremen has a new player: " + team.getPlayers().get(0).getName());
		System.out.println("Player " + player.getName() + " participated in trainingsession " + 
				player.getTrainingsession().get(0).getTitle() + " and improved to " + player.getSkillLevel());
		System.out.println("Player " + player2.getName() + " skipped the trainingsession " + 
				player2.getTrainingsession().get(0).getTitle() + " and worsened to " + player2.getSkillLevel());
//		System.out.println("Player " + player.getName() + " has joined " + player.getTeam().get(0).getTeamName());
//		System.out.println("Player " + player2.getName() + " has coach " + player2.getCoach().getName());
//		System.out.println("Player " + player.getName() + " has coach " + player.getCoach().getName());

	}

}
