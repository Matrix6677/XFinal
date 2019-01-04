package cn.ziav.seckill.manager;

import java.util.Date;
import javax.persistence.Table;

@Table(name = "`success_killed`")
public class SuccessKilled {
  private Long seckillId;

  private Long userPhone;

  private Byte state;

  private Date createTime;

  public static SuccessKilled valueOf(Long seckillId, Long userPhone) {
    SuccessKilled successKilled = new SuccessKilled();
    successKilled.seckillId = seckillId;
    successKilled.userPhone = userPhone;
    successKilled.state = 0;
    return successKilled;
  }

  public Long getSeckillId() {
    return seckillId;
  }

  public void setSeckillId(Long seckillId) {
    this.seckillId = seckillId;
  }

  public Long getUserPhone() {
    return userPhone;
  }

  public void setUserPhone(Long userPhone) {
    this.userPhone = userPhone;
  }

  public Byte getState() {
    return state;
  }

  public void setState(Byte state) {
    this.state = state;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
}
