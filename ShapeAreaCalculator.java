public class ShapeAreaCalculator {
    	public int calculateArea(int side){
        		return side * side;
                	}
                    	public double calculateArea(double length, double width){
                        	return length * width;
                            }
                            public static void main(String[] args) {
                            		ShapeAreaCalculator calc = new ShapeAreaCalculator();

                                    		System.out.println("Square area (side 5): " + calc.calculateArea(5));
                                            		System.out.println("Rectangle area (5.5 x 3.2): " + calc.calculateArea(5.5, 3.2));
                                                    	}
                                                        }
}
