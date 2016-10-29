package com.debuglife.codelabs.designpattern.behavioralpattern_7_19.memento;

/**
 * 简单描述下：新建原始类时，value被初始化为egg，后经过修改，将value的值置为niu，
 * 最后倒数第二行进行恢复状态，结果成功恢复了。其实我觉得这个模式叫“备份-恢复”模式最形象。
 */
public class TestMemento {

	public static void main(String[] args) {
		TestMemento t = new TestMemento();
		
		// 创建原始类  
        Original origi = t.new Original("egg");  
  
        // 创建备忘录  
        Storage storage = t.new Storage(origi.createMemento());  
  
        // 修改原始类的状态  
        System.out.println("初始化状态为：" + origi.getValue());  
        origi.setValue("niu");  
        System.out.println("修改后的状态为：" + origi.getValue());  
  
        // 回复原始类的状态  
        origi.restoreMemento(storage.getMemento());  
        System.out.println("恢复后的状态为：" + origi.getValue());  
	}

    private class Original {  
        private String value;
        
        public Original(String value) {  
            this.value = value;  
        }  

        public String getValue() {  
            return value;  
        }  
      
        public void setValue(String value) {  
            this.value = value;  
        }  
      
        public Memento createMemento(){  
            return new Memento(value);  
        }  
          
        public void restoreMemento(Memento memento){  
            this.value = memento.getValue();  
        }  
    }  
    
    private class Memento {  
        private String value;  
      
        public Memento(String value) {  
            this.value = value;  
        }  
      
        public String getValue() {  
            return value;  
        }  
      
        public void setValue(String value) {  
            this.value = value;  
        }  
    }  

    private class Storage {  
        private Memento memento;  
          
        public Storage(Memento memento) {  
            this.memento = memento;  
        }  
      
        public Memento getMemento() {  
            return memento;  
        }  
      
        public void setMemento(Memento memento) {  
            this.memento = memento;  
        }  
    }  
}
