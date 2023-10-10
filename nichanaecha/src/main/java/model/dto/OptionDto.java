package model.dto;

import java.util.List;

public class OptionDto {
	
	// 필드
	private List<String> manufacturer;
	private List<String> carClass;
	private List<String> fuelType;
	private String minYear;
	private String maxYear;
	private int minMileage;
	private int maxMileage;
	private Long minPrice;
	private Long maxPrice;
	
	// 생성자
	public OptionDto() {}
	
	public OptionDto(List<String> manufacturer, List<String> carClass, List<String> fuelType, String minYear,
			String maxYear, int minMileage, int maxMileage, Long minPrice, Long maxPrice) {
		super();
		this.manufacturer = manufacturer;
		this.carClass = carClass;
		this.fuelType = fuelType;
		this.minYear = minYear;
		this.maxYear = maxYear;
		this.minMileage = minMileage;
		this.maxMileage = maxMileage;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}


	// getter setter
	public List<String> getManufacturer() {
		return manufacturer;
	}


	public void setManufacturer(List<String> manufacturer) {
		this.manufacturer = manufacturer;
	}


	public List<String> getCarClass() {
		return carClass;
	}


	public void setCarClass(List<String> carClass) {
		this.carClass = carClass;
	}


	public List<String> getFuelType() {
		return fuelType;
	}


	public void setFuelType(List<String> fuelType) {
		this.fuelType = fuelType;
	}


	public String getMinYear() {
		return minYear;
	}


	public void setMinYear(String minYear) {
		this.minYear = minYear;
	}


	public String getMaxYear() {
		return maxYear;
	}


	public void setMaxYear(String maxYear) {
		this.maxYear = maxYear;
	}


	public int getMinMileage() {
		return minMileage;
	}


	public void setMinMileage(int minMileage) {
		this.minMileage = minMileage;
	}


	public int getMaxMileage() {
		return maxMileage;
	}


	public void setMaxMileage(int maxMileage) {
		this.maxMileage = maxMileage;
	}


	public long getMinPrice() {
		return minPrice;
	}


	public void setMinPrice(Long minPrice) {
		this.minPrice = minPrice;
	}


	public long getMaxPrice() {
		return maxPrice;
	}


	public void setMaxPrice(Long maxPrice) {
		this.maxPrice = maxPrice;
	}


	@Override
	public String toString() {
		return "OptionDto [manufacturer=" + manufacturer + ", carClass=" + carClass + ", fuelType=" + fuelType
				+ ", minYear=" + minYear + ", maxYear=" + maxYear + ", minMileage=" + minMileage + ", maxMileage="
				+ maxMileage + ", minPrice=" + minPrice + ", maxPrice=" + maxPrice + "]";
	}
	
	
	
	
	
	
	
}



