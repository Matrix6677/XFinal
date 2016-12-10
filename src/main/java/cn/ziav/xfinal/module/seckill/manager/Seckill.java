package cn.ziav.xfinal.module.seckill.manager;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import cn.ziav.xfinal.common.data.IEntity;

@Entity
public class Seckill implements IEntity<Long> {
	@Id
	private long seckillId;

	private String name;

	private int number;

	private Date startTime;
	private Date endTime;
	private Date createTime;

	@Override
	public Long getId() {
		return seckillId;
	}

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void reduce() {
		--number;
	}

}
