using System.Text.Json.Serialization;
using Dashdoc.API.Server.StartupConfigurations;

var builder = WebApplication.CreateBuilder(args);

# region Register App Services
builder.Services.AddMemoryCache();
builder.Services.AddCors();
builder.Services.AddControllers().AddJsonOptions(x =>
{
    // serialize enums as strings in api responses (e.g. Role)
    x.JsonSerializerOptions.Converters.Add(new JsonStringEnumConverter());

    // ignore omitted parameters on models to enable optional params (e.g. User update)
    // x.JsonSerializerOptions.DefaultIgnoreCondition = JsonIgnoreCondition.WhenWritingNull;
});

// Automapper
builder.Services.AddAutoMapper(typeof(Program));

// Swagger Support
// Learn more about configuring Swagger/OpenAPI at https://aka.ms/aspnetcore/swashbuckle
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

// DB Config
builder.Services.RegisterDashdocDatabase(builder.Configuration);

// Local Services Configuration
builder.Services.RegisterAppServices();

// Auth
builder.Services.ConfigureDashdocAuthorization(builder.Configuration);

#endregion

var app = builder.Build();

// Configure the HTTP request pipeline.
if (app.Environment.IsEnvironment("Local"))
{
    app.UseSwagger();
    app.UseSwaggerUI();
    app.MapControllers().AllowAnonymous();
}
else
{
    app.MapControllers();
}

app.UseAuthentication();
app.UseAuthorization();
app.UseStaticFiles();

// Defers app routing to the React App
app.MapFallbackToFile("/index.html");

app.Run();