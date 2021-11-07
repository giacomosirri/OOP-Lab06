package it.unibo.oop.lab.exception1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Testing class for PositionOutOfBound.
 * 
 */
public final class BaseRobotTest {

	private static final String POSITION_FAIL = "Something went wrong: "
			+ "robot position should be fine here :(";
	private static final String BATTERY_FAIL = "Something went wrong: "
			+ "battery should have no problems here :(";
	
    /**
     * Simple test for testing a robot moving, wandering the available
     * environment.
     * 
     */
    @Test
    public void testRobotMovementBase() {
        /*
         * FIRST OF ALL, take a look to "TestWithExceptions". Read the source and the
         * comments very carefully.
         */
    	
        /*
         * 1) Create a Robot with battery level 100
         */
        final Robot r1 = new Robot("SimpleRobot", 100);
        // checking if robot is in position x=0; y=0
        assertEquals("[CHECKING ROBOT INIT POS X]", 0, r1.getEnvironment().getCurrPosX());
        assertEquals("[CHECKING ROBOT INIT POS Y]", 0, r1.getEnvironment().getCurrPosY());
        
        /*
         * 2) Move the robot right until it touches the world limit
         */
        for (int i = 0; i < RobotEnvironment.WORLD_X_UPPER_LIMIT; i++) {
            // check if position is coherent
            try { 
            	r1.moveRight();
            } catch (PositionOutOfBoundException e) {
            	fail(POSITION_FAIL);
            }
        }
        // reached the right limit of the world: cannot move right
        try {
        	r1.moveRight();
        	fail("An exception over robot position is expected here :/");
        } catch (PositionOutOfBoundException e) {
        	assertNotNull(e.getMessage());
        	System.out.println(e.getMessage());
        } catch (NotEnoughBatteryException e) {
        	fail(BATTERY_FAIL);
        }
        // checking positions x=50; y=0
        assertEquals("[MOVING RIGHT ROBOT POS X]", RobotEnvironment.WORLD_X_UPPER_LIMIT, r1.getEnvironment().getCurrPosX());
        assertEquals("[MOVING RIGHT ROBOT POS Y]", 0, r1.getEnvironment().getCurrPosY());

        /*
         * 3) Move to the top until it reaches the upper right corner of the world
         */
        for (int i = 0; i < RobotEnvironment.WORLD_Y_UPPER_LIMIT; i++) {
            // check if position is coherent
            try { 
            	r1.moveUp();
            } catch (PositionOutOfBoundException e) {
            	fail(POSITION_FAIL);
            }
        }
        // reached the upper limit of the world: cannot move up
        try {
        	r1.moveUp();
        	fail("An exception over robot position is expected here :/");
        } catch (PositionOutOfBoundException e) {
        	assertNotNull(e.getMessage());
        	System.out.println(e.getMessage());
        } catch (NotEnoughBatteryException e) {
        	fail(BATTERY_FAIL);
        }
        // checking positions x=50; y=80
        assertEquals("[MOVING RIGHT ROBOT POS X]", RobotEnvironment.WORLD_X_UPPER_LIMIT, r1.getEnvironment().getCurrPosX());
        assertEquals("[MOVING RIGHT ROBOT POS Y]", RobotEnvironment.WORLD_Y_UPPER_LIMIT, r1.getEnvironment().getCurrPosY());
    }

    /**
     * Simple test for testing robot battery.
     * 
     */
    @Test
    public void testRobotBatteryBase() {
        final Robot r2 = new Robot("SimpleRobot2", 20);
        
        /*
         * Repeatedly move the robot up and down until the battery is completely
         * exhausted.
         */
        while (r2.getBatteryLevel() > 0) {
            try {
            	r2.moveUp();
            	r2.moveDown();
            } catch (NotEnoughBatteryException e) {
            	fail(BATTERY_FAIL);
            }
        }
        // verify battery level: not enough battery to move in any direction
        try {
        	r2.moveUp();
        	r2.moveDown();
        	r2.moveRight();
        	r2.moveLeft();
        	fail("An exception over battery level is expected here :/");
        } catch (NotEnoughBatteryException e) {
        	assertNotNull(e.getMessage());
        	System.out.println(e.getMessage());
        }
        // verify position: same as start position
        assertEquals("[CHECKING ROBOT INIT POS Y]", 0, r2.getEnvironment().getCurrPosY());
        
        // re-charge battery
        r2.recharge();
        
        // verify battery level
        assertEquals(100, r2.getBatteryLevel(), 0);
    }
}
