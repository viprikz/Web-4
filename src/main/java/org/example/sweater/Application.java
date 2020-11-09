package org.example.sweater;
import org.example.sweater.entitys.QuestionE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@EnableAutoConfiguration
public class Application implements CommandLineRunner {

    @Autowired
    private QuestionsRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        repository.deleteAll();

        // save a couple of customers
        QuestionE k = new QuestionE("Какая сегодня погода?", "Подскажите пожалуйста какая сегодня будет погода на камсаке", 1);
        repository.save(k);
        repository.save(new QuestionE("Какой сегодня день недели", "Какой сегодня день недели совсем вылетает из головы", 2));
        repository.save(new QuestionE("Подскажите хороший сайт с торрент трекерами", "Срочно подскажите хороший сайт с трекерами", 3));
        repository.save(new QuestionE("Не прогружаются картинки в вк", "У меня созрела неприятная проблема.Я хотел отправить пикчу под постом в ВК но заметил что нужной мне пикчи нету. Прошу заметить, что в общей сложности на телефоне у меня 1059 фотографии, из них 748 скачаны с ВК, но ВКонтакте не показывает и половины фотографий. На фотографии показаны только 2 фотки хотя, напомню, у меня их 748.", 4));
        repository.save(new QuestionE("Сброс пароля с телефона explay n1", "\"Забыл пароль, особо важного там нет. Открыл типо заводское меню с помощью комбинаций клавиш Питание + Кнопка громкости. Что дальше делать не знаю. Помогите!", 5));
        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (QuestionE questionE : repository.findAll()) {
            System.out.println(questionE);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Какая сегодня погода?'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByShortcontent("Какая сегодня погода?"));

        System.out.println("Customers found with findByLastName('Какой сегодня день недели совсем вылетает из головы'):");
        System.out.println("--------------------------------");
    }

}