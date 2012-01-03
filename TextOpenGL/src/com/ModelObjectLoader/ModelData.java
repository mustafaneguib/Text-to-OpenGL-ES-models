package com.ModelObjectLoader;

import java.io.Serializable;

public class ModelData implements Serializable{
	
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
