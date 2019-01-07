/**
 * @description:
 * @author: fancying
 * @create: 2019-01-07 21:44
 **/
package creational.factorymethod;

public class SmsSender implements Sender {

    @Override
    public void send() {
        System.out.println("this is sms sender!");
    }
}