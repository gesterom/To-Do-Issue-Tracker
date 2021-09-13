package HttpProtocol;

public enum HttpCodes {
	Continue (100,"Continue"),
	Switching_Protocol(101,"Switching Protocol"),
	Early_Hints(103,"Early Hints"),
	OK(200,"OK"),
	Creted(201,"Created"),
	Accepted(202,"Accepted"),
	NonAuthoritativeInfo(203,"Non-Authoritative Information"),
	NoContent(204 ,"No Content"),
	ResetContent(205, "Reset Content"),
	PartialContent(206 ,"Partial Content"),
	MultipleChoise(300 ,"Multiple Choices"),
	MovetPermanently(301 ,"Moved Permanently"),
	Found(302,"Found"),
	SeeOther(303,"See Other"),
	NotModified(304,"Not Modified"),
	UseProxy(305, "Use Proxy"),
	TemporaryRedirection(307,"Temporary Redirect"),
	BadRequest(400,"Bad Request"),
	Unauthorized(401,"Unauthorized"),
	PaymentRequired(402 ,"Payment Required"),
	Forbidden(403 ,"Forbidden"),
	NotFound(404,"Not Found"),
	MethodNotAllowed(405,"Method Not Allowed"),
	NotAcceptable(406, "Not Acceptable"),
	ProxyAuthenticationRequired(407, "Proxy Authentication Required"),
	RequestTimeout(408, "Request Timeout"),
	Conflict(409,"Conflict"),
	Gone(410,"Gone"),
	LengthRequired(411,"Length Required"),
	PreconditionFailed(412, "Precondition Failed)"),
	RequestEntityTooLarge(413, "Request Entity Too Large"),
	RequestURITooLong(414," Request-URI Too Long"),
	UnsupportedMediaType(415, "Unsupported Media Type"),
	RequestedRangeNotSatisfiable(416, "Requested Range Not Satisfiable"),
	ExpectationFailed(417 ,"Expectation Failed"),
	InternalServerError(500 ,"Internal Server Error"),
	NotImplemented(501,"Not Implemented"),
	BadGateway(502, "Bad Gateway"),
	ServiceUnavailable(503 ,"Service Unavailable"),
	GatewayTimeout(504,"Gateway Timeout"),
	HTTPVersionNotSupported(505 ,"HTTP Version Not Supported");
	
	
	

	

	private String code;
	private String resson;
	
	HttpCodes(int i, String string) {
		this.code =  String.valueOf(i);
		this.resson = string;
	}
	public String statusLine() {
		return "HTTP/1.1" + " " + code + " " + this.resson + "\r\n";
	}

}
