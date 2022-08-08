package animations;

public class PlayerActions {
	
	public static class Directions {
		public static final int LEFT = 0;
		public static final int RIGHT = 2;
		public static final int UP = 1;
		public static final int DOWN = 3;
	}
	
	
	public static final int PAUSE = 0;
	public static final int RUNNING = 1;
	public static final int HIT = 2;
	public static final int JUMP = 3;
	public static final int ATTACK_1 = 4;
	public static final int ATTACK_JUMP_1 = 5;
	public static final int ATTACK_JUMP_2 = 6;
	public static final int GROUND = 7;
	public static final int FALLING = 8;
	
	public static int getPlayerAmount(int playerAction) {
		switch(playerAction) {
		case RUNNING:
			return 6;
		case PAUSE:
			return 5;
		case HIT:
			return 4;
		case JUMP:
		case ATTACK_1:
		case ATTACK_JUMP_1:
		case ATTACK_JUMP_2:
			return 3;
		case GROUND:
			return 2;
		case FALLING:
		default:
			return 1;
		}
	}
}
