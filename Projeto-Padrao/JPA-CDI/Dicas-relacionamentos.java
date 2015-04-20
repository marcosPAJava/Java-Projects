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
