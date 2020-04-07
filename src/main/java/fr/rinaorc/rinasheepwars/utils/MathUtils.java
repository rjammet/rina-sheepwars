package fr.rinaorc.rinasheepwars.utils;

import java.util.Random;

public class MathUtils {
	
	public static final float nanoToSec = 1.0E-9F;
	  public static final float FLOAT_ROUNDING_ERROR = 1.0E-6F;
	  public static final float PI = 3.1415927F;
	  public static final float PI2 = 6.2831855F;
	  public static final float SQRT_3 = 1.73205F;
	  public static final float E = 2.7182817F;
	  public static final float radiansToDegrees = 57.295776F;
	  public static final float radDeg = 57.295776F;
	  public static final float degreesToRadians = 0.017453292F;
	  public static final float degRad = 0.017453292F;
	  static final int ATAN2_DIM = (int)Math.sqrt(16384.0D);
	  public static Random random = new Random();
	  
	  public MathUtils() {}
	  

	  public static final int random(int range) {
	    return random.nextInt(range + 1);
	  }
	  
	  public static final int random(int start, int end) {
	    return start + random.nextInt(end - start + 1);
	  }
	  
	  public static final boolean randomBoolean() {
	    return random.nextBoolean();
	  }
	  
	  public static final boolean randomBoolean(float chance) {
	    return random() < chance;
	  }
	  
	  public static final float random() {
	    return random.nextFloat();
	  }
	  
	  public static final float random(float range) {
	    return random.nextFloat() * range;
	  }
	  
	  public static final float random(float start, float end) {
	    return start + random.nextFloat() * (end - start);
	  }
	  
	  public static int nextPowerOfTwo(int value) {
	    if (value == 0) {
	      return 1;
	    }
	    value--;value |= value >> 1;
	    value |= value >> 2;
	    value |= value >> 4;
	    value |= value >> 8;
	    value |= value >> 16;
	    return value + 1;
	  }
	  
	  public static boolean isPowerOfTwo(int value) {
	    return (value != 0) && ((value & value - 1) == 0);
	  }
	  
	  public static int clamp(int value, int min, int max) {
	    if (value < min) {
	      return min;
	    }
	    if (value > max) {
	      return max;
	    }
	    return value;
	  }
	  
	  public static short clamp(short value, short min, short max) {
	    if (value < min) {
	      return min;
	    }
	    if (value > max) {
	      return max;
	    }
	    return value;
	  }
	  
	  public static float clamp(float value, float min, float max) {
	    if (value < min) {
	      return min;
	    }
	    if (value > max) {
	      return max;
	    }
	    return value;
	  }
	  
	  public static int floor(float x) {
	    return (int)(x + 16384.0D) - 16384;
	  }
	  
	  public static int floorPositive(float x) {
	    return (int)x;
	  }
	  
	  public static int ceil(float x) {
	    return (int)(x + 16384.999999999996D) - 16384;
	  }
	  
	  public static int ceilPositive(float x) {
	    return (int)(x + 0.9999999D);
	  }
	  
	  public static int round(float x) {
	    return (int)(x + 16384.5D) - 16384;
	  }
	  
	  public static int roundPositive(float x) {
	    return (int)(x + 0.5F);
	  }
	  
	  public static boolean isZero(float value) {
	    return Math.abs(value) <= 1.0E-6F;
	  }
	  
	  public static boolean isZero(float value, float tolerance) {
	    return Math.abs(value) <= tolerance;
	  }
	  
	  public static boolean isEqual(float a, float b) {
	    return Math.abs(a - b) <= 1.0E-6F;
	  }
	  
	  public static boolean isEqual(float a, float b, float tolerance) {
	    return Math.abs(a - b) <= tolerance;
	  }

}
