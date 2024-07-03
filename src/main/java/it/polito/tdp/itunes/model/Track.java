package it.polito.tdp.itunes.model;

import java.util.Objects;

public class Track {
	private Integer trackId;
	private String name;
	private String composer;
	private int milliseconds;
	private int bytes;
	private double unitPrice;
	private int mediaType;

	public Track(Integer trackId, String name, String composer, int milliseconds, int bytes, double unitPrice,
			int mediaType) {
		super();
		this.trackId = trackId;
		this.name = name;
		this.composer = composer;
		this.milliseconds = milliseconds;
		this.bytes = bytes;
		this.unitPrice = unitPrice;
		this.mediaType = mediaType;
	}

	@Override
	public String toString() {
		return name;
	}

	public Integer getTrackId() {
		return trackId;
	}

	public void setTrackId(Integer trackId) {
		this.trackId = trackId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComposer() {
		return composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	public int getMilliseconds() {
		return milliseconds;
	}

	public void setMilliseconds(int milliseconds) {
		this.milliseconds = milliseconds;
	}

	public int getBytes() {
		return bytes;
	}

	public void setBytes(int bytes) {
		this.bytes = bytes;
	}

	public double getUnitPrice() {
		return unitPrice;
	}


	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getMediaType() {
		return mediaType;
	}
	
	public void setMediaType(int mediaType) {
		this.mediaType = mediaType;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bytes, composer, mediaType, milliseconds, name, trackId, unitPrice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Track other = (Track) obj;
		return bytes == other.bytes && Objects.equals(composer, other.composer) && mediaType == other.mediaType
				&& milliseconds == other.milliseconds && Objects.equals(name, other.name)
				&& Objects.equals(trackId, other.trackId)
				&& Double.doubleToLongBits(unitPrice) == Double.doubleToLongBits(other.unitPrice);
	}
	
	
	
	
	
}
