package utility;

public class Time {

// Static methods
	
	public static long now(){ 								return System.currentTimeMillis(); 		}
	
	public static boolean isTimeLaterThanNow(long time){ 	return time > now(); 					}
	
	public static boolean isTimeLaterThanNow(int time){ 	return (long)time > now(); 				}
	
	public static long addTimeToNow(long time){ 			return now() + time; 					}
	
	public static long addTimeToNow(int time){ 				return now() + (long)time; 				}
	
	public static long diffFromNow(long time){ 				return now() - time; 					}
}
