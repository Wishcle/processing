import processing.core.PApplet;

public class PerlinReddit extends PApplet {

	public static void main(String[] args) {
		main("PerlinReddit");
	}

	/*

	CONTROLS:

	space: toggle mode
	w: add 100 dots
	q: set the number of dots to the maximum
	p: regenerate the perlin field
	~: take a screenshot
	c: begin video capture
	r: reset
	any other key: add 1 dot

	 */

	Perline[] p;
	float z;
	int col1 = color(255,150,0,255);
	int col2 = color(30,5,20,20);
	int[] col;
	float clock = 1;
	float dotWidth = 5;
	float sway = 3;       //how strongly the noise affects particle movement in mode 1, the higher the less
	float zdiff = 20;      //rate of change for the z axis, how the rivers shift
	int num;              //number of active particles
	int numDots = 10000;   //number of possible particles
	int speed = 5;       //scale of particle speeds
	int toggle = 1;
	int scale = 20;      //scale of the perlin noise: 1 = fine, 100 = big
	int b = 120;          //brightness of the particles
	int counter = 0;
	int countmax = 300;  //determines length between mode switch
	boolean record = false;

	class Perline{
		
		float x, y;
		float angle;
		float vel;
		int color;

		Perline(){
			x = random(width);
			y = random(height);
			angle = random(2*PI);
			vel = random(speed);
			color = col1;
		}

	}
	
	public void settings() {
		size(1200, 700);
	}

	public void setup(){
		noCursor();
		frameRate(30);
		strokeCap(ROUND);
		background(col2);
		
		num = 0;
		z = 0;
		p = new Perline[numDots];
		
		for(int i = 0; i < numDots; i++) {
			p[i] = new Perline();
		}
	}

	public void draw(){
		fill(col2);
		noStroke();
		rect(-1,-1,width+2,height+2);
		strokeWeight(dotWidth);
		
		Perline perl;
		float noise, weight;
		for(int i = 0; i < num; i++){
			perl = p[i];
			
			noise = noise(perl.x / scale, perl.y / scale, z / scale);
			weight = map(noise, 0, 1, dotWidth, 0);
//			strokeWeight(weight);
			
			noise = noise(perl.x / scale, perl.y / scale, z / scale);
			perl.vel = speed * noise;
			
			if(toggle == 0){
				// noise field mode: angle = flow field vector
				noise = noise(perl.x / scale, perl.y / scale, z / scale);
				perl.angle = map(noise, 0, 1, -PI, 3*PI);
			} else {
				// else: angle = 
				noise = noise(perl.x / scale, perl.y / scale, z / scale);
				perl.angle += (noise - 0.5) / sway;
			}
			
			// prev pos
			float tx = perl.x;
			float ty = perl.y;
			
			// update current position
			perl.x -= cos(perl.angle) * perl.vel;
			perl.y += sin(perl.angle) * perl.vel;

			// line from previous position to current position
			stroke(perl.color);
			line(perl.x, perl.y, tx, ty);
			
			// edge wrap
			perl.x = perl.x > width 	?  perl.x % width  : perl.x;
			perl.x = perl.x < 0		?  perl.x + width  : perl.x;
			perl.y = perl.y > height	?  perl.y % height : perl.y;
			perl.y = perl.y < 0 		?  perl.y + height : perl.y;
			
			p[i] = perl;
		}
		
		// time ? not sure
		z += zdiff / 50;
		
		// time reset ? not sure
		if(z > 1000000) {
			z = 0;
		}
		
		// toggles between flow field mode and random mode?
		counter++;
		if(counter > countmax){
			counter = 0;
			toggle++;
			toggle = toggle % 2;
			noiseSeed(floor(random(1000)));
		}
		
		if(record) {
			saveFrame("frames/####.jpg");
		}
	}


	public void keyPressed(){
		if(key==' '){
			toggle++;
			toggle = toggle % 2;
			noiseSeed(floor(random(1000)));
		} else if(key=='p') {
			noiseSeed(floor(random(1000)));
		} else if(key=='r') {
			setup();
		} else if(key=='w') {
			num += 100;
		} else if(key=='q') {
			num = numDots-1;
		} else if(key=='`') {
			saveFrame();
		} else {
			num++;
			if (num >= numDots) {
				setup();
			} else {
				noStroke();
				fill(p[num-1].color);
				ellipse(p[num-1].x, p[num-1].y, dotWidth*4, dotWidth*4);
			}
		}
		if (num >= numDots) {
			setup();
		}

	}

	public void mouseClicked(){
		num++;
		if (num >= numDots) {
			setup();
		} else {
			noStroke();
			fill(p[num-1].color);
			ellipse(p[num-1].x, p[num-1].y, dotWidth*4, dotWidth*4);
		}
	}

}
