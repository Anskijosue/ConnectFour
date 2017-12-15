package gameFour;

/***********************************************
 * @author Kevin Trujillo
 * Defines interface 
 *
 ***********************************************/
public interface Player { 
void init (Boolean color);
String name ();
int move ();
void inform (int i); }