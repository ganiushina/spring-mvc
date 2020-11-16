package com.geekbrains.spring.hibernate;

import com.geekbrains.spring.hibernate.model.Customer;
import com.geekbrains.spring.hibernate.model.Order;
import com.geekbrains.spring.hibernate.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Long orderID = null;
        Long orderID1 = null;

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        Scanner in = new Scanner(System.in);
        System.out.print("Введите свои имя : ");
        String customerName = in.nextLine();
        System.out.print("Введите название продукта : ");
        String productName = in.nextLine();
        System.out.print("Введите стоимость продукта : ");
        Integer productCoast = in.nextInt();

            Session session = null;
            try {
                session = factory.getCurrentSession();
                session.beginTransaction();
                Product product1 = new Product();
                product1.setName(productName);
                product1.setCoast(productCoast);

                session.save(product1);


                Customer customer1 = new Customer();
                customer1.setName(customerName);

                session.save(customer1);

                Order order = new Order();
                order.setId(1l); // если удалить эту строку, появляется ошибка.
                order.setCustomer(customer1);
                order.setProduct(product1);
                session.save(order);

                session.flush();

                session.getTransaction().commit();
                System.out.println(product1.getName());


                session = factory.getCurrentSession();
                session.beginTransaction();

                System.out.print("Какой товар вы хотите посмотреть? : ");
                Scanner sc = new Scanner(System.in);
                String productName1 = sc.nextLine();


                List<Order> orderList = getOrders(session);


                for (int i = 0; i < orderList.size() ; i++) {
                    if (orderList.get(i).getProduct().getName().equals(productName1))
                    orderID = orderList.get(i).getId();
                }

                Order order1 = session.get(Order.class, orderID);

                System.out.println(order1.getCustomer().getName() + " " + order1.getProduct().getName());

                session.getTransaction().commit();


                session = factory.getCurrentSession();
                session.beginTransaction();

                System.out.print("Какой товар вы хотите удалить? : ");
                Scanner scanner1 = new Scanner(System.in);
                String productName2 = scanner1.nextLine();


                List<Order> orderList1 = getOrders(session);


                for (int i = 0; i < orderList1.size() ; i++) {
                    if (orderList1.get(i).getProduct().getName().equals(productName2))
                        orderID1 = orderList1.get(i).getId();
                }


                deleteOrder(session, orderID1);

                session.getTransaction().commit();


            } finally {
                factory.close();
                if (session != null) {
                    session.close();
                }
            }
    }

    private static List<Order> getOrders(Session session) {
        return (List<Order>)session.createQuery("SELECT o FROM Order o"
                                     , Order.class).
                            getResultList();

    }
    private static void deleteOrder(Session session, Long id ) {
          session.createQuery("DELETE FROM Order o WHERE o.id = :id").
                setParameter("id", id).
                executeUpdate();

    }


}
