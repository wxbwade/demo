package com.example.demo;

import com.example.demo.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.time.*;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Java8Learn {

    @Test
    public void test() throws Exception{

        // 1.常用函数式接口
        // 1.1函数式接口Predicate<T> 函数修饰符T->boolean 方法test
        List<String> lst = Arrays.asList("st1","","st3","st4");
        Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
        List<String> nonEmptyLst = lst.stream().filter(nonEmptyStringPredicate).collect(Collectors.toList());
        Assert.assertEquals(Arrays.asList("st1","st3","st4"),nonEmptyLst);

        IntPredicate evenNumbers = (int i) -> i % 2 == 0;
        Assert.assertEquals(true, evenNumbers.test(100));

        // 1.2函数式接口Consumer<T> 函数修饰符T->void 方法accept
        Consumer<String> consumer = (String str) -> System.out.println(str);
        lst.stream().forEach(consumer);

        DoubleConsumer doubleConsumer = (double dou) -> System.out.println(dou);
        doubleConsumer.accept(10.0);

        // 1.3函数式接口Function<T> 函数修饰符T->R 方法apply
        List<String> lst2 = Arrays.asList("1","4","3","15","8");
        Function<String,Integer> fun = Integer::parseInt;
        List<Integer> intLst = lst2.stream().map(fun).sorted().collect(Collectors.toList());
        System.out.println(intLst);

        System.out.println(fun.apply("50"));

        // 2.方法引用
        // 2.1静态方法的方法引用
        List<Integer> staticLst = lst2.stream().map(Integer::parseInt).collect(Collectors.toList());
        System.out.println(staticLst);

        // 2.2实例方法的方法引用
        List<String> lst3 = Arrays.asList("B","z","K","A","d");
        lst3.sort(String::compareToIgnoreCase);
        System.out.println(lst3);

        // 2.3现有对象的方法引用
        List<User> userList = Arrays.asList(
                new User(1,"吴彦祖",26,new Date()),
                new User(3,"刘德华",35,new Date()),
                new User(2,"张学友",40,new Date()),
                new User(6,"张学友",42,new Date()),
                new User(4,"黎明",44,new Date()),
                new User(5,"郭富城",44,new Date())
        );
        List<User> sortedList = userList.stream().sorted(Comparator.comparing(User::getId)).collect(Collectors.toList());
        System.out.println(sortedList);

        // 2.4复合使用
        // 比较器复合使用
        userList.sort(Comparator.comparing(User::getId).reversed().thenComparing(User::getAge));
        System.out.println(userList);
        // 谓词复合使用
        Predicate<User> predicate1 = a -> a.getId() > 1;
        Predicate<User> predicate2 = predicate1.and(a -> a.getAge() > 38);
        System.out.println(userList.stream().filter(predicate2).map(User::getName).collect(Collectors.toList()));
        // 函数复合使用
        Function<Integer,Integer> f1 = i -> i+i;
        Function<Integer,Integer> f2 = i -> i*i;
        int result = f1.andThen(f2).apply(6);
        Assert.assertEquals(144,result);

        // 3.流的使用
        // 3.1流的筛选和切片
        List<String> streamList = userList.stream()
                                        .filter(a -> a.getAge() > 35)
                                        .map(User::getName)
                                        .distinct()
                                        .limit(4)
                                        .collect(Collectors.toList());
        streamList.stream().forEach(System.out::println);
        System.out.println(streamList.stream().count());

        // 3.2流的查找与判断
        boolean isAllMan = userList.stream().allMatch(a -> a.getAge() > 30);

        Optional<User> man = userList.stream().filter(a -> a.getAge() > 36).findAny();
        man.ifPresent(a -> System.out.println(a.getName()));

        // 3.3流的归约
        // 求和
        int totalAge = userList.stream().map(User::getAge).reduce(0,Integer::sum);
        // 最大值
        Optional<Integer> maxAge = userList.stream().map(User::getAge).reduce(Integer::max);
        maxAge.ifPresent(System.out::println);
        // 最小值
        Optional<Integer> minAge = userList.stream().map(User::getAge).reduce(Integer::min);
        minAge.ifPresent(System.out::println);

        // 3.4创建无限流
        Stream.iterate(0,n -> n+5).limit(5).forEach(System.out::println);
        Stream.generate(Math::random).limit(3).forEach(System.out::println);

        // 4.收集器
        // 4.1收集器的归约与汇总
        long userCount = userList.stream().collect(Collectors.counting());
        int totalAge2 = userList.stream().collect(Collectors.summingInt(User::getAge));
        double avgAge = userList.stream().collect(Collectors.averagingInt(User::getAge));
        System.out.println("用户人数：" + userCount + ";年龄总和：" + totalAge2 + ";平均年龄：" + avgAge);
        System.out.println(userList.stream().map(User::getName).collect(Collectors.joining(",")));

        // 4.2收集器的分组与多级分组
        Map<String, List<User>> simplegroupMap = userList.stream().collect(Collectors.groupingBy(User::getName));
        System.out.println(simplegroupMap);

        Map<String, Map<Integer, List<User>>> multigroupMap = userList.stream().collect(
                Collectors.groupingBy(User::getName,Collectors.groupingBy(User::getAge))
        );
        System.out.println(multigroupMap);

        Map<String, Optional<User>> calcgroupMap = userList.stream()
                .collect(Collectors.groupingBy(User::getName,
                        Collectors.maxBy(Comparator.comparing(User::getAge))));
        System.out.println(calcgroupMap);

        // 4.3收集器的分区
        Map<Boolean, List<User>> partitioningMap = userList.stream().collect(Collectors.partitioningBy(u -> {
            if(u.getAge()>40) return true;
            else return false;}
        ));
        System.out.println(partitioningMap);

        // 5.Optional
        Optional<User> optUser = Optional.empty();  // 空Optional
        Optional<User> nullableOpt = Optional.ofNullable(null); // 允空Optional
        Optional<User> nonullOpt = Optional.of(new User(7,"胡歌",30,new Date(118,5,18))); // 非空Optional

        System.out.println(nonullOpt.get());
        Optional<String> userOpt = nonullOpt.map(User::getName);
        userOpt.ifPresent(System.out::println);
        nullableOpt.orElse(new User(1,"KK",20,new Date()));

        // 6.日期API
        LocalDate localDate = LocalDate.of(2018,6,5);   // 2018-06-05
        System.out.println(localDate);
        LocalDate nowTime = LocalDate.now();    // 2018-06-05
        System.out.println(nowTime);

        LocalTime localTime = LocalTime.of(13,25,16);   // 13:25:16
        System.out.println(localTime);
        LocalTime localTime1 = LocalTime.parse("13:25:16");
        System.out.println(localTime1);

        LocalDateTime localDateTime = LocalDateTime.of(localDate,localTime);
        LocalDateTime localDateTime1 = LocalDateTime.of(2018,7,7,13,26,18);
        System.out.println(localDateTime1);

        System.out.println(localDate.atTime(12,40,25));
        System.out.println(localDateTime1.toLocalTime());

        // 机器时间
        Duration duration = Duration.between(localTime,localDateTime1.toLocalTime());  // 针对秒和纳秒
        System.out.println(duration);

        Period period = Period.between(localDate,localDateTime1.toLocalDate()); // 针对天
        System.out.println(period);

        // 格式化日期
        LocalDate addDate = localDate.minusDays(1);
        LocalDate addDate1 = localDate.plusMonths(1);
        LocalDate addDate2 = localDate.with(ChronoField.DAY_OF_MONTH,13);
        System.out.println("addDate:"+addDate+";addDate1:"+addDate1+";addDate2:"+addDate2);

        String formatedDate = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        String formatedDate1 = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println("BASIC_ISO_DATE:" + formatedDate + ";ISO_LOCAL_DATE:" + formatedDate1);

        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println(localDate.format(df));

        // 时区信息
        ZoneId zoneId = ZoneId.of("Europe/Rome");
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        System.out.println(zdt);

        Chronology jpChro = Chronology.ofLocale(Locale.JAPAN);
        ChronoLocalDate now = jpChro.dateNow();
        System.out.println(now);

    }
}
