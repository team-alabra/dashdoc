using System.Text.Json.Serialization;
using Dashdoc.API.Server.StartupConfigurations;

var builder = WebApplication.CreateBuilder(args);

// builder.Configuration.AddSecretsManagerConfig();

# region Register App Services
builder.Services.AddMemoryCache();
builder.Services.AddCors();
builder.Services.AddControllers().AddJsonOptions(x =>
{
    // serialize enums as strings in api responses (e.g. Role)
    x.JsonSerializerOptions.Converters.Add(new JsonStringEnumConverter());

    // ignore omitted parameters on models to enable optional params (e.g. User update)
    x.JsonSerializerOptions.DefaultIgnoreCondition = JsonIgnoreCondition.WhenWritingNull;
});;

// Swagger Support
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

// AppConfig Setup
// builder.Services.Configure<RdsAppSettings>(builder.Configuration);

// DB Config
builder.Services.RegisterDashdocDatabase();

// Cognito config

// Stripe Config
// builder.Services.RegisterStripeClient();

// Local Services Configuration
builder.Services.RegisterAppServices();

#endregion

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsEnvironment("Local"))
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();
app.UseAuthorization();
app.MapControllers();

app.MapFallbackToFile("/index.html");

app.Run();