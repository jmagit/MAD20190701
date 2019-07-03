package com.example.demo.domains.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the city database table.
 * 
 */
@Entity
@Table(name="city")
@NamedQuery(name="City.findAll", query="SELECT c FROM City c")
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="city_id")
	private int cityId;

	private String city;

	@Column(name="last_update")
	private Timestamp lastUpdate = (new Timestamp(System.currentTimeMillis()));

	//bi-directional many-to-one association to Address
	@OneToMany(mappedBy="city")
	private Set<Address> addresses;

	//bi-directional many-to-one association to Country
	@ManyToOne
	@JoinColumn(name="country_id")
	private Country country;

	public City() {
	}

	public City(int cityId) {
		super();
		this.cityId = cityId;
	}

	public City(int cityId, String city, Country country) {
		super();
		this.cityId = cityId;
		this.city = city;
		this.country = country;
	}

	public int getCityId() {
		return this.cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Set<Address> getAddresses() {
		return this.addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

	public Address addAddress(Address address) {
		getAddresses().add(address);
		address.setCity(this);

		return address;
	}

	public Address removeAddress(Address address) {
		getAddresses().remove(address);
		address.setCity(null);

		return address;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", city=" + city + ", country=" + country + "]";
	}

}