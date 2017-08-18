package cn.ziav.xfinal.common.data;

@FunctionalInterface
public interface EntityBuilder<PK extends Comparable<PK>, T extends IEntity<PK>> {

  T newInstance(PK id);

}
