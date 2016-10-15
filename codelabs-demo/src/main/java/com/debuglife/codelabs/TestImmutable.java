package com.debuglife.codelabs;

import java.util.Date;

/**
 * 
要写出这样的类，需要遵循以下几个原则：
1）immutable对象的状态在创建之后就不能发生改变，任何对它的改变都应该产生一个新的对象。
2）Immutable类的所有的属性都应该是final的。
3）对象必须被正确的创建，比如：对象引用在对象创建过程中不能泄露(leak)。
4）对象应该是final的，以此来限制子类继承父类，以避免子类改变了父类的immutable特性。
5）如果类中包含mutable类对象，那么返回给客户端的时候，返回该对象的一个拷贝，而不是该对象本身（该条可以归为第一条中的一个特例）

当然不完全遵守上面的原则也能够创建immutable的类，比如String的hashcode就不是final的，但它能保证每次调用它的值都是一致的，无论你多少次计算这个值，它都是一致的，因为这些值的是通过计算final的属性得来的！
另外，如果你的Java类中存在很多可选的和强制性的字段，你也可以使用建造者模式来创建一个immutable的类

使用Immutable类的好处：
1）Immutable对象是线程安全的，可以不用被synchronize就在并发环境中共享
2）Immutable对象简化了程序开发，因为它无需使用额外的锁机制就可以在线程间共享
3）Immutable对象提高了程序的性能，因为它减少了synchroinzed的使用
4）Immutable对象是可以被重复使用的，你可以将它们缓存起来重复使用，就像字符串字面量和整型数字一样。你可以使用静态工厂方法来提供类似于valueOf（）这样的方法，它可以从缓存中返回一个已经存在的Immutable对象，而不是重新创建一个。

immutable也有一个缺点就是会制造大量垃圾，由于他们不能被重用而且对于它们的使用就是”用“然后”扔“，字符串就是一个典型的例子，它会创造很多的垃圾，给垃圾收集带来很大的麻烦。当然这只是个极端的例子，合理的使用immutable对象会创造很大的价值。

 *
 */


public class TestImmutable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	

}

final class ImmutableReminder{
    private final Date remindingDate;
  
    public ImmutableReminder (Date remindingDate) {
        if(remindingDate.getTime() < System.currentTimeMillis()){
            throw new IllegalArgumentException("Can not set reminder” + “ for past time: " + remindingDate);
        }
        this.remindingDate = new Date(remindingDate.getTime());
    }
  
    public Date getRemindingDate() {
        return (Date) remindingDate.clone();
    }
}


final class Contacts {

    private final String name;
    private final String mobile;

    public Contacts(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
    }
  
    public String getName(){
        return name;
    }
  
    public String getMobile(){
        return mobile;
    }
}
