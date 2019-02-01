public class NBody {

	public static double readRadius(String fileName) {
		In in = new In(fileName);
		in.readLine();

		return in.readDouble();



	}
	public static Body[] readBodies(String fileName) {
		In in = new In(fileName);
		Body[] bodies = new Body[in.readInt()];
		in.readDouble();

		int i = 0;

		while(i < bodies.length) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();
			bodies[i] = new Body(xxPos, yyPos, xV, yV, m, img);
			++i; 

		}	
		return bodies;

	}

	public static void main(String[] args) {

		double T = Double.parseDouble(args[0]);
		double dT = Double.parseDouble(args[1]);
		String filename = args[2];

		Body[] bodies = NBody.readBodies(filename); // reads the bodies in the filename
		double radius = NBody.readRadius(filename); // sets radius for the universe.

		StdDraw.setScale(-radius,radius);
		StdDraw.clear();
		StdDraw.picture(0.0, 0.0, "images/starfield.jpg");
		for(Body body : bodies) {
            body.draw();
        }
        
		 //StdDraw.enableDoubleBuffering();
         //StdDraw.show();
     	double[] xForces = new double[bodies.length];
     	double[] yForces = new double[bodies.length];
        for(int time = 0;time < T; time += dT){
         	
         	for(int i = 0; i < bodies.length; i++) {
         		xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
         		yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
         	}
         	for(int i = 0; i < bodies.length; i++) {
         		bodies[i].update(dT,xForces[i],yForces[i]);
       
         	}
         	StdDraw.clear();
         	StdDraw.enableDoubleBuffering();
         	StdDraw.picture(0.0, 0.0, "images/starfield.jpg");
         	for(Body body: bodies) {
         		body.draw();
         	}
         	StdDraw.show();
         	StdDraw.pause(10);


         		
         	
         }
        StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                  bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
		}
        
	}
}

