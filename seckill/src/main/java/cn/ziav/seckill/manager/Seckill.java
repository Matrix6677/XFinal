package cn.ziav.seckill.manager;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "`seckill`")
public class Seckill implements Serializable {
  @Id private Long seckillId;

  private String name;

  private Integer number;

  private Date startTime;

  private Date endTime;

  private Date createTime;

  public Long getSeckillId() {
    return seckillId;
  }

  public void setSeckillId(Long seckillId) {
    this.seckillId = seckillId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name == null ? null : name.trim();
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
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
}
