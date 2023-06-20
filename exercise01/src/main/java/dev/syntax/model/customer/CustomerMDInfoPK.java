package dev.syntax.model.customer;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class CustomerMDInfoPK{
	private Date date;
	private String customerNum;

}
