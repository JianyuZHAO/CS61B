
public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	static final double G=6.67e-11;
	
	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos=xP;
		yyPos=yP;
		xxVel=xV;
		yyVel=yV;
		mass=m;
		imgFileName=img;
	}
	
	public Planet(Planet p){
		xxPos=p.xxPos;
		yyPos=p.yyPos;
		xxVel=p.xxVel;
		yyVel=p.yyVel;
		mass=p.mass;
		imgFileName=p.imgFileName;
	}
	
	public double calcDistance(Planet p){
		return Math.sqrt((p.xxPos-this.xxPos)*(p.xxPos-this.xxPos)+(p.yyPos-this.yyPos)*(p.yyPos-this.yyPos));
	}
	
	public double calcForceExertedBy(Planet p){
		double r = calcDistance(p);
		return (G*this.mass*p.mass)/(r*r);
	}
	
	public double calcForceExertedByX(Planet p){
		double F = calcForceExertedBy(p);
		return F * ((p.xxPos-this.xxPos)/calcDistance(p));
	}
	
	public double calcForceExertedByY(Planet p){
		double F = calcForceExertedBy(p);
		return F * ((p.yyPos-this.yyPos)/calcDistance(p));
	}
	
	public double calcNetForceExertedByX(Planet[] planets){
		double F = 0;
		for(Planet p : planets){
			if(!p.equals(this)){
				F = F + calcForceExertedByX(p);
			}
		}
		return F;
	}
	
	public double calcNetForceExertedByY(Planet[] planets){
		double F = 0;
		for(Planet p : planets){
			if(!p.equals(this)){
				F = F + calcForceExertedByY(p);
			}
		}
		return F;
	}
	
	public void update(double dt, double fX, double fY){
		
		this.xxVel = this.xxVel + fX/this.mass*dt;
		this.yyVel = this.yyVel + fY/this.mass*dt;
		this.xxPos = this.xxPos + this.xxVel*dt;
		this.yyPos = this.yyPos+ this.yyVel*dt;
	}
	
	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, this.imgFileName);
	}
	
}