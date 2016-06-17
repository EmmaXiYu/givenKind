package org.givenkind.dto;

public class CompletedTransactionsDTO {
	
	private Long id;
	
	Long donorProfileId;
	
	private Long npProfileId;

	private Long donorItemId;
	
	private Long npItemId;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDonorProfileId() {
		return donorProfileId;
	}

	public void setDonorProfileId(long donorProfileId) {
		this.donorProfileId = donorProfileId;
	}

	public long getNpProfileId() {
		return npProfileId;
	}

	public void setNpProfileId(long npProfileId) {
		this.npProfileId = npProfileId;
	}

	public Long getDonorItemId() {
		return donorItemId;
	}

	public void setDonorItemId(Long donorItemId) {
		this.donorItemId = donorItemId;
	}

	public Long getNpItemId() {
		return npItemId;
	}

	public void setNpItemId(Long npItemId) {
		this.npItemId = npItemId;
	}
}
