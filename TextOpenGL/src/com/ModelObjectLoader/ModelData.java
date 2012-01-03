/******************
 * 
 * 
    Copyright (C) 2012  Mustafa Neguib
    Copyright (C) 2008-2012 MN Tech Solutions

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

import java.io.Serializable;

public class ModelData implements Serializable{
	
	
	private static final long serialVersionUID = -2249607888406869604L;
	
	private float [] vertexBuffer;
	private float [] textureBuffer;
	private short[] facesBuffer;
	private int textureImageResourceId;
	
	
	public ModelData(float[] vertexBuffer, float[] textureBuffer,
			short[] facesBuffer, int textureImageResourceId) {
		super();
		this.vertexBuffer = vertexBuffer;
		this.textureBuffer = textureBuffer;
		this.facesBuffer = facesBuffer;
		this.textureImageResourceId = textureImageResourceId;
	}
	public float[] getVertexBuffer() {
		return vertexBuffer;
	}
	public void setVertexBuffer(float[] vertexBuffer) {
		this.vertexBuffer = vertexBuffer;
	}
	public float[] getTextureBuffer() {
		return textureBuffer;
	}
	public void setTextureBuffer(float[] textureBuffer) {
		this.textureBuffer = textureBuffer;
	}
	public short[] getFacesBuffer() {
		return facesBuffer;
	}
	public void setFacesBuffer(short[] facesBuffer) {
		this.facesBuffer = facesBuffer;
	}
	public int getTextureImageResourceId() {
		return textureImageResourceId;
	}
	public void setTextureImageResourceId(int textureImageResourceId) {
		this.textureImageResourceId = textureImageResourceId;
	}
	
	

}
