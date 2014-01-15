package ch2.p73.CustomWritable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

/**
 * 自定义Writable类
 * @author PLAYMORE
 *
 */
public class Circle implements Writable{
    private float radius,x,y;
    public float GetRadius(){return radius;}
    public float GetX(){return x;}
    public float GetY(){return y;}
    public void readFields(DataInput in)throws IOException{
        radius=in.readFloat();
        x=in.readFloat();
        y=in.readFloat();
    }
    public void write(DataOutput out)throws IOException{
        out.writeFloat(radius);
        out.writeFloat(x);
        out.writeFloat(y);
    }
    public int CompareTo(Circle cl){
        if(cl.radius==this.radius){
        	 return 0;
        }
           
        if(cl.x>this.x){
        	 return 1;
        }
           
        if(cl.y<this.y){
        	return -1;
        }
		return 0;
            
    }
}