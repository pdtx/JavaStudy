/**
 * @description: 多个工厂方法模式
 * @author: fancying
 * @create: 2019-01-07 21:12
 **/
package creational.factorymethod;

/**
 * 多个工厂方法模式，是对普通工厂方法模式的改进，在普通工厂方法模式中，如果传递的字符串出错，则不能正确创建对象
 * 而多个工厂方法模式是提供多个工厂方法，分别创建对象
 * */
public class SendFactoryMore {

    public Sender produceMail(){
        return new MailSender();
    }

    public Sender produceSms(){
        return new SmsSender();
    }

    public static void main(String[] args) {
        SendFactoryMore factory = new SendFactoryMore();
        Sender sender = factory.produceMail();
        sender.send();
    }
}