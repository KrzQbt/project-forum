package pl.edu.wszib.project.forum.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.project.forum.dao.IDBPopulateDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class DBPopulateDAOImpl implements IDBPopulateDAO {
    @Autowired
    Connection connection;


    @Override
    public void populateFromSQLDump() {

        String sql1 =  "INSERT INTO `forumthread` (`id`, `description`, `name`) VALUES\n" +
                "(3, 'Wątek o programowaniu w java, można w nim zadawać pytania', 'Programowanie w Javie'),\n" +
                "(2, 'Dyskusje o bazach danych', 'Bazy danych'),\n" +
                "(4, 'Dyskusje na dowolny temat', 'Hydepark');";

        String sql2= "INSERT INTO `user` (`id`, `imageLink`, `login`, `pass`, `role`) VALUES\n" +
                        "(1, NULL, 'polska', 'polska', 'USER'),\n" +
                        "(2, NULL, 'admin', 'admin', 'ADMIN'),\n" +
                        "(3, NULL, 'banned', 'banned', 'USER'),\n" +
                        "(4, NULL, 'junit', 'junit', 'USER'),\n" +
                        "(5, NULL, 'nowy1', 'nowy1', 'USER');";

        String sql3 ="INSERT INTO `post` (`id`, `content`, `dateTime`, `forumthread_id`, `user_id`) VALUES\n" +
                "(5, 'witam na wątku', '2021-03-02 20:12:26', 2, 2),\n" +
                "(6, 'Zapraszam do zadawania pytań', '2021-03-03 10:56:46', 3, 2),\n" +
                "(7, 'Wolę .NET i C#', '2021-03-03 11:27:35', 3, 3),\n" +
                "(8, 'Typewriter XOXO master cleanse meditation, YOLO franzen fanny pack. Cardigan hashtag vice green juice. Viral 3 wolf moon fanny pack you probably haven\\'t heard of them lyft lomo tacos. ', '2021-03-03 11:50:08', 4, 2),\n" +
                "(9, 'Whatever hella yr pinterest vice trust fund roof party knausgaard pug sartorial swag health goth etsy. Aesthetic hell of seitan jean shorts XOXO meditation 90\\'s freegan single-origin coffee. ', '2021-03-03 11:51:15', 3, 1),\n" +
                "(10, 'Wolf fingerstache chia kogi twee tousled bitters master cleanse butcher wayfarers cloud bread. Brooklyn mixtape microdosing, hexagon polaroid hot chicken plaid wayfarers tumeric kombucha.', '2021-03-03 11:51:49', 3, 1),\n" +
                "(11, 'Vape wayfarers synth farm-to-table subway tile bicycle rights mustache venmo street art polaroid. Tbh kickstarter food truck swag kale chips truffaut mixtape pok pok chia poke. Lyft flexitarian meditation tumeric humblebrag pok pok.', '2021-03-03 11:52:58', 3, 3),\n" +
                "(12, 'Next level chambray plaid, farm-to-table bespoke godard literally slow-carb. PBR&B cornhole godard raclette vinyl neutra. Kinfolk disrupt ethical drinking vinegar, listicle distillery swag wolf la croix.', '2021-03-03 11:53:52', 3, 3),\n" +
                "(13, 'Beard butcher paleo vape aesthetic, glossier messenger bag af poke franzen forage. Vegan cornhole iPhone lo-fi mumblecore af narwhal polaroid deep v. ', '2021-03-03 11:54:18', 3, 3),\n" +
                "(14, 'Etsy 8-bit pitchfork, biodiesel polaroid prism adaptogen brooklyn godard. Gluten-free pug drinking vinegar chambray celiac. Paleo meggings mustache semiotics banjo DIY yuccie waistcoat street art occupy kogi flexitarian aesthetic next level cloud bread.', '2021-03-04 00:01:35', 3, 1),\n" +
                "(15, 'Whatever 8-bit shoreditch ramps hell of, next level offal affogato gochujang. Meggings gentrify wayfarers truffaut, retro taiyaki farm-to-table normcore austin readymade butcher adaptogen. ', '2021-03-04 00:02:47', 3, 1),\n" +
                "(16, 'Before they sold out put a bird on it bushwick tbh vaporware, roof party bicycle rights flannel tilde kinfolk affogato hot chicken plaid whatever hexagon.', '2021-03-04 00:03:27', 3, 1),\n" +
                "(17, 'Beard iceland gastropub knausgaard, marfa selvage hexagon echo park franzen distillery craft beer dreamcatcher farm-to-table heirloom pinterest.', '2021-03-04 00:03:52', 3, 1),\n" +
                "(18, 'Czesc tu nowy', '2021-03-04 00:06:01', 3, 5),\n" +
                "(19, 'hello', '2021-03-04 00:06:18', 2, 5),\n" +
                "(20, 'Whatever 8-bit shoreditch ramps hell of, next level offal affogato gochujang. Meggings gentrify wayfarers truffaut, retro taiyaki farm-to-table normcore austin readymade butcher adaptogen.', '2021-03-04 00:06:26', 2, 5),\n" +
                "(21, 'Before they sold out put a bird on it bushwick tbh vaporware, roof party bicycle rights flannel tilde kinfolk affogato hot chicken plaid whatever hexagon.', '2021-03-04 00:07:01', 4, 3);";
//                "INSERT INTO `post` (`id`, `content`, `dateTime`, `forumthread_id`, `user_id`) VALUES\n" +
//                        "(5, 'witam na wątku', '2021-03-02 20:12:26', 2, 2),\n" +
//                        "(6, 'Zapraszam do zadawania pytań', '2021-03-03 10:56:46', 3, 2),\n" +
//                        "(7, 'Wolę .NET i C#', '2021-03-03 11:27:35', 3, 3),\n" +
//                        "(8, 'Typewriter XOXO master cleanse meditation, YOLO franzen fanny pack. Cardigan hashtag vice green juice. Viral 3 wolf moon fanny pack you probably haven\\'t heard of them lyft lomo tacos. ', '2021-03-03 11:50:08', 4, 2),\n" +
//                        "(9, 'Whatever hella yr pinterest vice trust fund roof party knausgaard pug sartorial swag health goth etsy. Aesthetic hell of seitan jean shorts XOXO meditation 90\\'s freegan single-origin coffee. ', '2021-03-03 11:51:15', 3, 1),\n" +
//                        "(10, 'Wolf fingerstache chia kogi twee tousled bitters master cleanse butcher wayfarers cloud bread. Brooklyn mixtape microdosing, hexagon polaroid hot chicken plaid wayfarers tumeric kombucha.', '2021-03-03 11:51:49', 3, 1),\n" +
//                        "(11, 'Vape wayfarers synth farm-to-table subway tile bicycle rights mustache venmo street art polaroid. Tbh kickstarter food truck swag kale chips truffaut mixtape pok pok chia poke. Lyft flexitarian meditation tumeric humblebrag pok pok.', '2021-03-03 11:52:58', 3, 3),\n" +
//                        "(12, 'Next level chambray plaid, farm-to-table bespoke godard literally slow-carb. PBR&B cornhole godard raclette vinyl neutra. Kinfolk disrupt ethical drinking vinegar, listicle distillery swag wolf la croix.', '2021-03-03 11:53:52', 3, 3),\n" +
//                        "(13, 'Beard butcher paleo vape aesthetic, glossier messenger bag af poke franzen forage. Vegan cornhole iPhone lo-fi mumblecore af narwhal polaroid deep v. ', '2021-03-03 11:54:18', 3, 3);\n" +
//                        "\n" ;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql1);
            preparedStatement.execute();
        }catch (SQLException throwables){
//            throwables.printStackTrace();
            throwables.printStackTrace();
        }
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql2);
            preparedStatement.execute();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql3);
            preparedStatement.execute();
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }


    }
}
