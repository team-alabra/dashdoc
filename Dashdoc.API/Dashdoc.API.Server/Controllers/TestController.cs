using Dashdoc.API.Domain.Abstract;
using Microsoft.AspNetCore.Mvc;

namespace Dashdoc.API.Server.Controllers;

[ApiController]
[Route("/api/test")]
public class TestController: ControllerBase
{
    private readonly ITestRepository _repo;
    private readonly ILogger _logger;
    public TestController(ITestRepository repo)
    {
        _repo = repo;
    }

    // TODO - replace with real Provider routes
    [HttpGet("all")]
    public async Task<ActionResult> Get()
    {
        try
        {
            var result = await _repo.GetAll();

            return Ok(result);
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex);
            return Problem("Problem retrieving Data");
        }
    }
}