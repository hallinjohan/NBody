public class Body {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static final double gravity = 6.67*Math.pow(10,-11);

	public Body(double _xxPos, double _yyPos, double _xxVel, double _yyVel,double _mass, String _imgFileName) {
	 	/* binds the instance variables to the parameters */
		 xxPos = _xxPos;
		 yyPos = _yyPos;
		 xxVel = _xxVel;
		 yyVel = _yyVel;
		 mass = _mass;
		 imgFileName = _imgFileName;



	}

	public Body(Body b) {
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;

	}

	public double calcDistance(Body b) {
		/* Calculates the Distance between two bodies.*/
		double distance = Math.sqrt(Math.pow((b.xxPos- this.xxPos),2) + Math.pow((b.yyPos - this.yyPos) ,2));

		return distance;


	}

	public double calcForceExertedBy(Body b) {
		/* Calculates the force exerted by one body onto the body in mind. */
		if (this.xxPos == b.xxPos && this.yyPos == b.yyPos) {

			return 0.0;
		}

		double force = (gravity * this.mass* b.mass) / Math.pow(calcDistance(b), 2);
		return force;


	}


	public double calcForceExertedByX(Body b) {
		/* Calculates the force exerted by one body onto the body in mind.  */
		if (this.xxPos == b.xxPos && this.yyPos == b.yyPos) {

			return 0.0;

		}
		double force_x = (calcForceExertedBy(b) * (b.xxPos - this.xxPos)) / calcDistance(b);
		return force_x;




	}

		public double calcForceExertedByY(Body b) {
		if (this.xxPos == b.xxPos && this.yyPos == b.yyPos) {

			return 0.0;

		}

		double force_y = (calcForceExertedBy(b) * (b.yyPos - this.yyPos)) / calcDistance(b);
		return force_y;
		



	}
	public double calcNetForceExertedByX(Body[] bodyList) {


		double net_force = 0;


		for(Body b: bodyList) {
			net_force += calcForceExertedByX(b);
			

			
		}

		return net_force;
	}

	public double calcNetForceExertedByY(Body[] bodyList) {
		double net_forceY = 0;
		for(Body b: bodyList) {
			net_forceY += calcForceExertedByY(b);
			

			
		}
		return net_forceY;

	}

	public void update(double dt,double fX, double fY) {
		double accel_X = fX/ this.mass ;
		double accel_Y = fY / this.mass;
		double new_velX = (this.xxVel + dt * accel_X);
		double new_VelY =  (this.yyVel+ dt* accel_Y);
		this.xxVel = new_velX;
		this.yyVel = new_VelY;
		this.xxPos = this.xxPos + dt*this.xxVel;
		this.yyPos =  this.yyPos + dt*this.yyVel; 

	}
	
	public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "./images/" + this.imgFileName);
    }


}