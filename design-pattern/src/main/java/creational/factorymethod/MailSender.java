/**
 * @description:
 * @author: fancying
 * @create: 2019-01-07 21:43
 **/
package creational.factorymethod;

public class MailSender implements Sender {

    @Override
    public void send() {
        System.out.println("this is mail sender!");
    }
}