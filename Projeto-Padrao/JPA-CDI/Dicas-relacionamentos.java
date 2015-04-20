O relacionamento OneToMany é utilizado quando uma entidade tem uma lista de outra entidade, um Cellular tem muitas Calls (ligações),
mas uma Call (ligação) só pode ter um Cellular. O relacionamento OneToMany é representado por uma lista como atibuto, pois está 
relacionado a mais de um registro.

@Entity
public class Call {
 
    @Id
    @GeneratedValue
    private int id;
 
    @ManyToOne
    @JoinColumn(name = "cellular_id")
    private Cellular cellular;
 
    private long duration;
    
    
    Sobre o código acima:

    Foi utilizada a anotação @ManyToOne.Note que já foi utilizada a anotação @JoinColumn para definir quem será o dono do 
    relacionamento.    O lado do relacionamento que tiver a anotação @ManyToOne será sempre dominante. Não existe a opção 
    de utilizar mappedBy dentro da anotação @ManyToOne

    Para realizar um relacionamento bidirecional é necessário alterar a entidade Cellular (que foi criada na página anterior). 
    Veja como ficará a entidade:
    
    @Entity
    public class Cellular {
 
    @Id
    @GeneratedValue
    private int id;
 
    @OneToOne(mappedBy = "cellular") 
    private Person person;
 
    @OneToMany(mappedBy = "cellular")
    private List<Call> calls;
 
    private int number;
    
    Sobre o código acima:

    Foi utilizada a anotação @OneToMany. Essa anotação deve ser colocada sobre uma coleção.
    Foi utilizado o mappedBy para indicar que esse relacionamento não é o lado dominante.

Todo o relacionamento necessita que umas das entidades seja a “dona desse relacionamento”. Ser dona do relacionamento nada mais
é do ter a chave estrangeira na tabela do banco de dados. No exemplo acima é possível ver na classe Call a utilização da anotação
@JoinColumn.  Essa anotação indica que a chave estrangeira ficará dentro da tabela Call. O atributo mappedBy deve apontar para o 
nome do atributo e não para o nome da classe.




ManyToMany (muitos para muitos) Unidirecional e Bidirecional

Um exemplo para um relacionamento ManyToMany poderia que uma pessoa pode ter vários cachorros e um cachorro pode ter várias pessoas
(imagine um cachorro que more numa casa com 15 pessoas).Para a abordagem ManyToMany é necessário utilizar uma tabela adicional para 
realizar a junção desse relacionamento. Teríamos uma tabela person (pessoa), uma tabela dog (cachorro) e uma tabela de 
relacionamento chamada person_dog. Tabela person_dog teria apenas o id da pessoa, e o id cachorro indicando qual pessoa tem qual
cachorro.

@Entity
public class Person {
 
    @Id
    @GeneratedValue
    private int id;
 
    private String name;
 
    @ManyToMany
    @JoinTable(name = "person_dog", joinColumns = @JoinColumn(name = "person_id"), 
    inverseJoinColumns = @JoinColumn(name = "dog_id"))
    private List<Dog> dogs;
 
    @OneToOne
    @JoinColumn(name = "cellular_id")
    private Cellular cellular;
 
    // get and set
}

Sobre o código acima:

    A anotação @ManyToMany é utilizada para esse tipo de relacionamento. A anotação @JoinTable foi utilizada para definir qual 
    tabela seria utilizada para realizar armazenas as entidades relacionadas. “name” define o nome da tabela. “joinColumns” define
    qual será o nome da coluna na tabela da entidade que é a dona do relacionamento. “inverseJoinColumns” define qual será o nome 
    da coluna  na tabela da entidade não dona do relacionamento.
    
    import java.util.List;
 
import javax.persistence.*;
 
@Entity
public class Dog {
 
    @Id
    @GeneratedValue
    private int id;
 
    private String name;
 
    @ManyToMany(mappedBy="dogs")
    private List<Person> persons;
 
    // get and set
}

É possível encontrar a notação @ManyToMany utilizando a opção mappedBy para determinar que a dona do relacionamento é a entidade 
Person.Todo o relacionamento necessita que umas das entidades seja a “dona desse relacionamento”. No caso do relacionamento 
ManyToMany a classe dona do relacionamento é a classe que não estiver utilizando a opção mappedBy na anotação @ManyToMany. 
Caso nenhuma classe esteja marcada com mappedBy o JPA irá tratar cada classe marcada com ManyToMany como dona do relacionamento.
O mappedBy deve apontar para o nome do atributo e não para o nome da classe.






