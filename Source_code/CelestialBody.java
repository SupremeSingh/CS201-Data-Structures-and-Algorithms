

/**
 * Celestial Body class for NBody
 * @author ola
 *
 */
public class CelestialBody 
{
	
	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName;
	
	/**
	 * Create a Body from parameters	
	 * @param xp initial x position
	 * @param yp initial y position
	 * @param xv initial x velocity
	 * @param yv initial y velocity
	 * @param mass of object
	 * @param filename of image for object animation
	 */
	
	public CelestialBody(double xp, double yp, double xv,
			             double yv, double mass, String filename)
	{
		// TODO: complete constructor
		myXPos = xp;
		myYPos = yp;
		myXVel = xv;
		myYVel = yv;
		myMass = mass;
		myFileName = filename;
	}

	/**
	 * Copy constructor: copy instance variables from one
	 * body to this body
	 * @param b used to initialize this body
	 */
	public CelestialBody(CelestialBody b){
		// TODO: complete constructor
		this(b.myXPos, b.myYPos, b.myXVel, b.myYVel, b.myMass, b.myFileName);
	}
	
	/*
	 * Return x-coordinate of this body.
	 * @return value of x-coordinate. 
	 */
	public double getX() {
		// TODO: complete method
		return myXPos;
	}
	/*
	 * Return y-coordinate of this body.
	 * @return value of y-coordinate. 
	 */
	public double getY() {
		// TODO: complete method
		return myYPos;
	}
	/*
	 * Return x-velocity of this body.
	 * @return value of x-velocity. 
	 */
	public double getXVel() {
		// TODO: complete method
		return myXVel;
	}
	/**
	 * Return y-velocity of this Body.
	 * @return value of y-velocity.
	 */
	public double getYVel() {
		// TODO: complete method
		return myYVel;
	}
	/*
	 * Return mass of this body.
	 * @return value of mass. 
	 */
	public double getMass() {
		// TODO: complete method
		return myMass;
	}
	/*
	 * Return name of file which stores image of this body.
	 * @return file name. 
	 */
	public String getName() {
		// TODO: complete method
		return myFileName;
	}

	/**
	 * Return the distance between this body and another
	 * @param b the other body to which distance is calculated
	 * @return distance between this body and b
	 */
	public double calcDistance(CelestialBody b) {
		// TODO: complete method
		double x_val = myXPos - b.myXPos;
		double y_val = myYPos - b.myYPos;
		return Math.sqrt((x_val*x_val) + (y_val*y_val));
	}
	/**
	 * Return the force exerted on this body by the other.
	 * @param p the other body which is exerting the force.
	 * @return Force exerted
	 */
	public double calcForceExertedBy(CelestialBody p) {
		// TODO: complete method
		double G = 6.67 * 1e-11;
		double F =  (G * myMass * p.myMass) / Math.pow(calcDistance(p),2) ;
		return F;
	}
	/**
	 * Return the X component of the force exerted on this body by the other.
	 * @param p the other body which is exerting the force.
	 * @return X component of the Force exerted
	 */
	public double calcForceExertedByX(CelestialBody p) {
		// TODO: complete method
		double F = calcForceExertedBy(p);
		double r = calcDistance(p);
		double x_val = - myXPos + p.myXPos;
		return (F * x_val) / r;
	}
	/**
	 * Return the Y component of the force exerted on this body by the other.
	 * @param p the other body which is exerting the force.
	 * @return Y component of the Force exerted
	 */
	public double calcForceExertedByY(CelestialBody p) {
		// TODO: complete method
		double F = calcForceExertedBy(p);
		double r = calcDistance(p);
		double y_val = - myYPos + p.myYPos;
		return (F * y_val) / r;
	}
	/**
	 * Return the sum of the X components of the forces exerted on this body by others, by using principle of superposition.
	 * @param bodies array of the other bodies which are exerting the force.
	 * @return X component of the Force exerted
	 */
	public double calcNetForceExertedByX(CelestialBody[] bodies) {
		// TODO: complete method
		double sum = 0;
		for (CelestialBody b : bodies)
		{
			if (! b.equals(this))
			{
				sum += calcForceExertedByX(b);
			}
		}
		return sum;
	}
	/**
	 * Return the sum of the Y components of the forces exerted on this body by others, by using principle of superposition.
	 * @param bodies array of the other bodies which are exerting the force.
	 * @return Y component of the Force exerted
	 */
	public double calcNetForceExertedByY(CelestialBody[] bodies) {
		// TODO: complete method
		double sum = 0;
		for (CelestialBody b : bodies)
		{
			if (! b.equals(this))
			{
				sum += calcForceExertedByY(b);
			}
		}
		return sum;
	}
	/**
	 * Return the updated values of myXPos, myYPos, myXVel, myYVel after the body has been in motion for deltaT time.
	 * @param deltaT is the step in time after which these values are to be updated, xforce and yforce are values of total force in X and Y direction on the body respectively.
	 * @return updated values of myXPos, myYPos, myXVel, myYVel
	 */
	public void update(double deltaT, 
			           double xforce, double yforce) {
		// TODO: complete method
		
		double ax, ay, nvx, nvy, nx, ny;
		
		ax = xforce / myMass;
		ay = yforce / myMass;
		
		nvx = myXVel + deltaT*ax;
		nvy = myYVel + deltaT*ay;
		
		nx = myXPos + deltaT*nvx;
		ny = myYPos + deltaT*nvy;
		
		myXPos = nx;
		myYPos = ny;
		myXVel = nvx;
		myYVel = nvy;

	}
	/**
	 * Places the picture of the planet at the position indicated by myXPos and myYPos. 
	 * @param "None"
	 * @return "None"		
	 */
	public void draw() {
		// TODO: complete method
		StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
	}
}
