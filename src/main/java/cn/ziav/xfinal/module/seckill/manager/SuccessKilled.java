package cn.ziav.xfinal.module.seckill.manager;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import cn.ziav.xfinal.common.data.IEntity;
import cn.ziav.xfinal.module.seckill.manager.SuccessKilled.PK;

@Entity
public class SuccessKilled implements IEntity<PK> {
	@EmbeddedId
	private PK pk;
	private short state;
	private Date createTime;

	@ManyToOne
	private Seckill seckill;

	@Embeddable
	public static class PK implements Comparable<PK>, Serializable {
		/** */
		private static final long serialVersionUID = -6928156742814812381L;
		private long seckillId;
		private long userPhone;

		public long getSeckillId() {
			return seckillId;
		}

		public void setSeckillId(long seckillId) {
			this.seckillId = seckillId;
		}

		public long getUserPhone() {
			return userPhone;
		}

		public void setUserPhone(long userPhone) {
			this.userPhone = userPhone;
		}

		@Override
		public int compareTo(PK o) {
			return 0;
		}

		public static PK valueOf(long seckillId2, long userPhone2) {
			PK pk = new PK();
			pk.seckillId = seckillId2;
			pk.userPhone = userPhone2;
			return pk;
		}

	}

	@Override
	public PK getId() {
		return pk;
	}

	public PK getPk() {
		return pk;
	}

	public void setPk(PK pk) {
		this.pk = pk;
	}

	public short getState() {
		return state;
	}

	public void setState(short state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Seckill getSeckill() {
		return seckill;
	}

	public void setSeckill(Seckill seckill) {
		this.seckill = seckill;
	}

	public static SuccessKilled valueOf(PK pk) {
		SuccessKilled successKilled = new SuccessKilled();
		successKilled.pk = pk;
		successKilled.state = 0;
		return null;
	}

}
