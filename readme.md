
    Spring mvc - https://habrahabr.ru/post/255773/ - Java-based конфигурирование embedded и embedded jetty
    Инициалайзеры - реализуя инициалайзер, мы можем определить web.xml в java config стиле.
    WebApplicationInitializer - содержит определение мэппингов и список спринг кофигов.
    AbstractSecurityWebApplicationInitializer - это тот же вэб инициалайзер, но с начальной настройкой фильтров spring-security/
    
    Наследник WebMvcConfigurerAdapter + @EnableWebMvc + @Configuration - это замена DispatcherServlet и 
    спринговогого веб конфига web-config.xml (http://memorynotfound.com/spring-mvc-xml-configuration-example/)
    Использование абстрактного класса WebMvcConfigurerAdapter облегчает настройку, предоставляя набор основных конфигурируемых параметров, 
    однако этот класс не является обязательным. Можно использовать более низкоуровневый WebMvcConfigurationSupport, можно использовать 
    для инициализации контекста практически базовый абстрактный AbstractAnnotationConfigDispatcherServletInitializer, 
    а можно строить конфигурацию на бинах, делая много ненужной работы.

    Наследник WebSecurityConfigurerAdapter + @EnableWebSecurity + @Configuration + @EnableGlobalMethodSecurity - это замена SecurityContext.xml подгружаемогого
    DispatcherServlet через web.xml
    
    http://developdevelop.blogspot.ru/2016_06_01_archive.html - Spring Security. Простая конфигурация
    https://ru.wikibooks.org/wiki/Spring_Security/%D0%9A%D0%BE%D0%BD%D1%84%D0%B8%D0%B3%D1%83%D1%80%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5_%D1%81_%D0%BF%D0%BE%D0%BC%D0%BE%D1%89%D1%8C%D1%8E_%D0%BF%D1%80%D0%BE%D1%81%D1%82%D1%80%D0%B0%D0%BD%D1%81%D1%82%D0%B2%D0%B0_%D0%B8%D0%BC%D1%91%D0%BD
    http://stackoverflow.com/questions/7391735/difference-between-access-permitall-and-filters-none
    http://stackoverflow.com/questions/22998731/httpsecurity-websecurity-and-authenticationmanagerbuilder - AbstractSecurityWebApplicationInitializer
    https://habrahabr.ru/post/245415/ - 6 способов: как добавить security для Rest сервиса в Java
    /*
            SecurityContextHolder - объект, содержащий текущий контекст безопасности (SecurityContext).
            SecurityContext, содержит объект Authentication и другую смежную информацию
            Principal - это отдельный авторизованный пользователь(или любая аутентифицированная сущность), с точки зрения Spring Security. Содержит в себе подробную информацию о пользователе.
            Authentication - содержит принципала
            GrantedAuthority - это полномочия(роли), которыми может обладать пользователь. В момент авторизации для пользователя определяются его роли, согласно которым он может
            иметь доступ к тем или иным ресурскам (или другими словами: определяется, какие полномочия имеет пользователь для работы с защищенными ремурсами).
            UserDetails - центральный интерфейс spring security. Предоставляет необходимую информацию для построения объекта Authentication из DAO объектов приложения
        или других источника данных системы безопасности.
            UserDetailsService (http://devcolibri.com/3810) - используется, чтобы создать UserDetails, когда передано имя пользователя в виде String (или идентификатор сертификата или что-то подобное).
            имеет реализацию след метода: UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

        AuthenticationManager - возвращает полностью заполненный экземпляр Authentication в случае успешной аутентификации.

        Для ВЭБа
            @EnableWebSecurity в связке с WebSecurityConfigurerAdapter классом работает над обеспечением аутентификации.
        По умолчанию в Spring Security встроены и активны HTTP аутентификация и аутентификация на базе веб форм.
     */