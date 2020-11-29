
public class NBody{
	
	public static double readRadius(String planetsTxtPath){
		In in = new In(planetsTxtPath);
		String radius = "0";
		if(in.exists() && !in.isEmpty()){
			if(in.hasNextLine()){
				in.readLine();
				if(in.hasNextLine()){
					radius = in.readLine();
				}
			}
		}
		return Double.valueOf(radius);
	}
	
	public static Planet[] readPlanets(String planetsTxtPath){
		Planet[] p = null;	
		In in = new In(planetsTxtPath);
		if(in.exists() && !in.isEmpty()){
			if(in.hasNextLine()){
				int planetNumber = Integer.valueOf(in.readLine());
				p = new Planet[planetNumber];
				if(in.hasNextLine()){
					String radius = in.readLine();
					for(int i=0; i<planetNumber; i++){
						if(in.hasNextLine()){
							double xxPos = in.readDouble();
							double yyPos = in.readDouble();
							double xxVel = in.readDouble();
							double yyVel = in.readDouble();
							double mass = in.readDouble();
							String imgFileName = in.readString();
							p[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
						}
					}
				}				
			}
		}
		return p;
	}
	
	public static void main(String[] args){
	
		String imageToDraw = "images/starfield.jpg";
		double T = Double.valueOf(args[0]);
		double dt = Double.valueOf(args[1]);
		String filename = args[2];
		Planet[] planets = readPlanets(filename);
		int numPlanet = planets.length;
		double radius = readRadius(filename);
		
		for(double t=0.0; t<T; t=t+dt){
			double[] xForces = new double[numPlanet];
			double[] yForces = new double[numPlanet];
			for(int i=0; i<numPlanet; i++){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for(int i=0; i<numPlanet; i++){
				planets[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.enableDoubleBuffering();
			StdDraw.setScale(radius*-1, radius);
			StdDraw.picture(0, 0, imageToDraw);
			for(Planet p : planets){
				p.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
		}
		
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
		                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
	}
}