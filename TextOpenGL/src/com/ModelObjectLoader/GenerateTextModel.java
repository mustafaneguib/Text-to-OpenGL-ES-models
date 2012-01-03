/******************
 * 
 * 
    Copyright (C) 2012  Mustafa Neguib
    Copyright (C) 2008-2012 MN Tech Solutions (www.mntechsolutions.net)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
Also add information on how to contact you by electronic and paper mail.
 * 
 * 
 */

package com.ModelObjectLoader;

import java.util.List;
import javax.microedition.khronos.opengles.GL10;
import android.content.Context;


public class GenerateTextModel {

	
	private ModelData model;
	private DrawModel modelDraw;
	private float x=0.0f;
	
	
	public GenerateTextModel()
	{	
		
	}
	
	public GenerateTextModel(Context context,List<ModelData> list, float x)
	{
		loadFromAssets(context,list,x);
	}
	
	
	
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	
	
	public ModelData getModel() {
		return model;
	}

	public void setModel(ModelData model) {
		this.model = model;
	}

	public DrawModel getModelDraw() {
		return modelDraw;
	}

	public void setModelDraw(DrawModel modelDraw) {
		this.modelDraw = modelDraw;
	}

	

	public void loadFromAssets(Context context,List<ModelData> list, float x) 
	{
			float width=3050;
			float size=32;
					
			float [] textures=new float[8];
			textures[0]=x/width;
			textures[1]=1;
			
			textures[2]=(x+size)/width;
			textures[3]=1;
			
			textures[4]=x/width;
			textures[5]=0;
			
			textures[6]=(x+size)/width;
			textures[7]=0;
					
			  model=  list.get(0);
		      modelDraw=new DrawModel(model.getVertexBuffer(),textures, model.getFacesBuffer(), model.getTextureImageResourceId());
		            
		
		
	}
	
	public void draw(GL10 gl)
	{
		modelDraw.draw(gl);
		
	}
	
	public void loadTextures(GL10 gl, Context context)
	{
		modelDraw.loadGLTexture(gl,context);
		
	}
	
}

