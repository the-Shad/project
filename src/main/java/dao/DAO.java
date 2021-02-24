package dao;

public class DAO {
	public static final String U_TABLE = "user";
	public static final String T_TABLE = "test";
	public static final String Q_TABLE = "question";
	public static final String A_TABLE = "answer";
	public static final String R_TABLE = "result";
	public static final String UR_TABLE = "user_role";
	public static final String REG_TABLE = "user_registration";
	
	public static final String ID = "id";
	public static final String USER_ID = "user_id";
	public static final String ROLE_ID = "role_id";
	
	public static final String FIRST_NAME = "first_name";
	public static final String LAST_NAME = "last_name";
	public static final String MIDDLE_NAME = "middle_name";
	public static final String LOGIN = "login";
	public static final String EMAIL = "email";
	public static final String ACTIVE = "activity";
	public static final String CREATED = "created";
	public static final String PASSWORD = "password";

	public static final String TEST_NAME = "test_name";
	public static final String DESCRIPTION = "description";
	
	public static final String TEST_ID = "test_id";
	public static final String QUESTION = "question";
	public static final String MULTI_RESPONCE = "multi_response";

	public static final String QUESTION_ID = "question_id";
	public static final String ANSWER = "answer";
	public static final String IS_CORRECT = "is_correct";

	public static final String RESULT = "result";
	public static final String AUTHOR_ID = "author_id";

	public static final String TOKEN = "token";
	
	public static final String UR_SEQ = "nextval('user_role_id_seq')";
	public static final String U_SEQ = "nextval('user_id_seq')";
	public static final String T_SEQ = "nextval('test_id_seq')";
	public static final String Q_SEQ = "nextval('question_id_seq')";
	public static final String A_SEQ = "nextval('answer_id_seq')";
	public static final String R_SEQ = "nextval('result_id_seq')";
	
	public static final String SELECT_EQUALS = "SELECT * FROM ? WHERE ? = ? ";
	public static final String OR_EQUALS = " OR ? = ?";
	public static final String SELECT = "SELECT * FROM ?";
	public static final String LIMIT = " ORDER BY id LIMIT ? OFFSET ?";
	public static final String DELETE = "DELETE FROM ? WHERE ? = ?";
	
	public static final String NEWS = "news";
	public static final String NAME = "name";
}
